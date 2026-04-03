package edu.ntnu.idi.idatt.ikbackend.service;

import edu.ntnu.idi.idatt.ikbackend.model.Organization;
import edu.ntnu.idi.idatt.ikbackend.model.Task;
import edu.ntnu.idi.idatt.ikbackend.repository.TaskRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service class for tasks. Handles logic related to managing tasks.
 */
@Service
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  /**
   * The createTask method takes in information about a task and adds it to the database.
   *
   * @param title,        the title or name of the task.
   * @param description,  a description of what the task entails.
   * @param organization, the organization the task belongs to.
   * @return the newly created task.
   */
  public Task createTask(String title, String description, Organization organization) {
    Task task = new Task();
    task.setTitle(title);
    task.setDescription(description);
    task.setCompleted(false);
    task.setOrganization(organization);

    return taskRepository.save(task);
  }

  /**
   * The method getTasksForOrganization takes in the organizations id and finds all tasks
   * referencing it.
   *
   * @param organizationId, the id of the organization.
   * @return the found tasks.
   */
  public List<Task> getTasksForOrganization(Long organizationId) {
    return taskRepository.findByOrganizationId(organizationId);
  }
}
