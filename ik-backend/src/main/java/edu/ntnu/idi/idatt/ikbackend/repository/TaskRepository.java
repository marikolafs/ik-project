package edu.ntnu.idi.idatt.ikbackend.repository;

import edu.ntnu.idi.idatt.ikbackend.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

  List<Task> findByOrganizationId(Long organizationId);
}
