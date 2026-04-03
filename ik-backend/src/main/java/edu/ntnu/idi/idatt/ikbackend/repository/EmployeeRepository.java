package edu.ntnu.idi.idatt.ikbackend.repository;

import edu.ntnu.idi.idatt.ikbackend.model.Employee;
import edu.ntnu.idi.idatt.ikbackend.model.Organization;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing employee entities. Extends JpaRepository to provide query
 * methods for accessing employees by specific attributes.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  /**
   * Retrieves employees by their email.
   *
   * @param email, the email address of the employee
   * @return an optional containing the found employee.
   */
  Optional<Employee> findByEmail(String email);

  /**
   * Retrieves all employees belonging to a given organization.
   *
   * @param organization, the organization whose employees are being retrieved.
   * @return a list of found employees.
   */
  List<Employee> findByOrganization(Organization organization);

}
