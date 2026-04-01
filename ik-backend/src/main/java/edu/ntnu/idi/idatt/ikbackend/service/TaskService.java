package edu.ntnu.idi.idatt.ikbackend.service;

import edu.ntnu.idi.idatt.ikbackend.model.Organization;
import edu.ntnu.idi.idatt.ikbackend.model.Task;
import edu.ntnu.idi.idatt.ikbackend.repository.TaskRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Task createTask(String title, String description, Organization organization) {
    Task task = new Task();
    task.setTitle(title);
    task.setDescription(description);
    task.setCompleted(false);
    task.setOrganization(organization);

    return taskRepository.save(task);
  }

  public List<Task> getTasksForOrganization(Long  organizationId) {
    return taskRepository.findByOrganizationId(organizationId);
  }
}
