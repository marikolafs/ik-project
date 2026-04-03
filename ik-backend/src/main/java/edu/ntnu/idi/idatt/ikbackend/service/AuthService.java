package edu.ntnu.idi.idatt.ikbackend.service;

import edu.ntnu.idi.idatt.ikbackend.model.Employee;
import edu.ntnu.idi.idatt.ikbackend.model.Organization;
import edu.ntnu.idi.idatt.ikbackend.repository.EmployeeRepository;
import edu.ntnu.idi.idatt.ikbackend.repository.OrganizationRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for authorization. Handles logic related to logging in and registering
 * organizations and employees.
 */
@Service
public class AuthService {

  private final EmployeeRepository employeeRepository;
  private final OrganizationRepository organizationRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthService(EmployeeRepository employeeRepository,
      OrganizationRepository organizationRepository, PasswordEncoder passwordEncoder) {
    this.employeeRepository = employeeRepository;
    this.organizationRepository = organizationRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * The login method checks if the given email exists in the database, and whether the given
   * password is correct.
   *
   * @param email,    the given user email.
   * @param password, the given user password.
   * @return employee, the newly created employee.
   * @throws Exception if the user does not exist or the password is incorrect.
   */
  public Employee login(String email, String password) throws Exception {
    Employee employee = employeeRepository.findByEmail(email)
        .orElseThrow(() -> new Exception("Invalid email"));
    if (!passwordEncoder.matches(password, employee.getPassword())) {
      throw new Exception("Invalid password");
    }
    return employee;
  }

  /**
   * The register method takes in given information about an organization and one of its employees
   * and adds these to their respective databases.
   *
   * @param orgName,  the name of the organization.
   * @param orgNr,    the organization number.
   * @param email,    the employees email address.
   * @param password, the employees chosen password.
   * @return employee, the newly created employee.
   */
  public Employee register(String orgName, String orgNr, String email, String password) {
    Organization organization = new Organization();
    organization.setName(orgName);
    organization.setOrgNr(orgNr);
    organizationRepository.save(organization);

    Employee employee = new Employee();
    employee.setEmail(email);
    employee.setPassword(passwordEncoder.encode(password));
    employee.setOrganization(organization);
    employee.setAdmin(true);
    employeeRepository.save(employee);
    return employee;
  }

  /**
   * The newEmployee method takes in information about an employee and adds them to the database.
   *
   * @param firstName,    the employees first name.
   * @param lastName,     the employees last name.
   * @param email,        the employees email address.
   * @param position,     the employees job position.
   * @param admin,        the employees administrator status.
   * @param password,     the employees password.
   * @param organization, the employees corresponding organization.
   * @return employee, the newly created employee.
   */
  public Employee newEmployee(String firstName, String lastName, String email, String position,
      boolean admin, String password, Organization organization) {
    Employee employee = new Employee();
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setEmail(email);
    employee.setPosition(position);
    employee.setAdmin(admin);
    employee.setPassword(passwordEncoder.encode(password));
    employee.setOrganization(organization);
    employeeRepository.save(employee);
    return employee;
  }
}
