package edu.ntnu.idi.idatt.ikbackend.controller;

import edu.ntnu.idi.idatt.ikbackend.model.Employee;
import edu.ntnu.idi.idatt.ikbackend.model.Organization;
import edu.ntnu.idi.idatt.ikbackend.model.Task;
import edu.ntnu.idi.idatt.ikbackend.repository.EmployeeRepository;
import edu.ntnu.idi.idatt.ikbackend.repository.TaskRepository;
import edu.ntnu.idi.idatt.ikbackend.service.TaskService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * REST controller for tasks. Controls services related to managing tasks.
 */
@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

  private final TaskRepository taskRepository;
  private TaskService taskService;
  private final EmployeeRepository employeeRepository;

  public TaskController(TaskService taskService, TaskRepository taskRepository,
      EmployeeRepository employeeRepository) {
    this.taskService = taskService;
    this.taskRepository = taskRepository;
    this.employeeRepository = employeeRepository;
  }

  /**
   * POST method for creating tasks. Takes in information about a task and adds it to the database,
   * with a reference to the logged in users organization.
   *
   * @param request, information pertaining to the task.
   * @param auth,    authentication for the logged-in user.
   * @return HTTP response with status code and the created task.
   */
  @PostMapping
  public ResponseEntity<?> createTask(@RequestBody Task request, Authentication auth) {

    String email = auth.getName();
    Employee employee = employeeRepository.findByEmail(email)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    Organization org = employee.getOrganization();

    Task task = taskService.createTask(request.getTitle(), request.getDescription(), org);

    return ResponseEntity.ok(task);
  }

  /**
   * GET method for retrieving tasks. Retrieves all tasks containing a reference to the logged in
   * users organization.
   *
   * @param auth, authorization for the logged-in user.
   * @return HTTP response with status code and the retrieved tasks.
   */
  @GetMapping
  public ResponseEntity<?> getTasks(Authentication auth) {
    String email = auth.getName();
    Employee employee = employeeRepository.findByEmail(email)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    Organization org = employee.getOrganization();

    List<Task> tasks = taskService.getTasksForOrganization(org.getId());

    return ResponseEntity.ok(tasks);
  }

  /**
   * PUT method for updating tasks. Allows tasks to be set as completed or not completed.
   *
   * @param id,      id for the task that should be updated.
   * @param request, information pertaining to the request.
   * @param auth,    authorization for the logged-in user.
   * @return HTTP response with status code and the updated task.
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> updateTask(@PathVariable("id") Long id, @RequestBody Task request,
      Authentication auth) {

    String email = auth.getName();
    Employee employee = employeeRepository.findByEmail(email)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    Organization org = employee.getOrganization();

    Optional<Task> optionalTask = taskRepository.findById(id);

    if (optionalTask.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    Task task = optionalTask.get();

    if (!task.getOrganization().getId().equals(org.getId())) {
      return ResponseEntity.status(403).build();
    }

    task.setTitle(request.getTitle());
    task.setDescription(request.getDescription());
    task.setCompleted(request.getCompleted());

    taskRepository.save(task);

    return ResponseEntity.ok(task);
  }
}
