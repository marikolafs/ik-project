package edu.ntnu.idi.idatt.ikbackend.service;

import edu.ntnu.idi.idatt.ikbackend.model.Employee;
import edu.ntnu.idi.idatt.ikbackend.repository.EmployeeRepository;
import edu.ntnu.idi.idatt.ikbackend.repository.OrganizationRepository;
import edu.ntnu.idi.idatt.ikbackend.model.Organization;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final EmployeeRepository employeeRepository;
  private final OrganizationRepository organizationRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthService(EmployeeRepository employeeRepository, OrganizationRepository organizationRepository, PasswordEncoder passwordEncoder) {
    this.employeeRepository = employeeRepository;
    this.organizationRepository = organizationRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Employee login(String email, String password) throws Exception {
    Employee employee = employeeRepository.findByEmail(email)
        .orElseThrow(() -> new Exception("Invalid email"));
    if (!passwordEncoder.matches(password, employee.getPassword())) {
      throw new Exception("Invalid password");
    }
    return employee;
  }

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
}
