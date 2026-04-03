package edu.ntnu.idi.idatt.ikbackend.repository;

import edu.ntnu.idi.idatt.ikbackend.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing task entities. Extends JpaRepository to provide query methods
 * for accessing tasks by specific attributes.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

  /**
   * Retrieves all tasks belonging to a given organization.
   *
   * @param organizationId, the organization whose tasks are being retrieved.
   * @return a list of found tasks.
   */
  List<Task> findByOrganizationId(Long organizationId);
}
