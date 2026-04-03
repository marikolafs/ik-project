package edu.ntnu.idi.idatt.ikbackend.controller;


import edu.ntnu.idi.idatt.ikbackend.dto.EmployeeDto;
import edu.ntnu.idi.idatt.ikbackend.dto.LoginRequest;
import edu.ntnu.idi.idatt.ikbackend.dto.RegisterRequest;
import edu.ntnu.idi.idatt.ikbackend.model.Employee;
import edu.ntnu.idi.idatt.ikbackend.repository.EmployeeRepository;
import edu.ntnu.idi.idatt.ikbackend.security.JwtUtil;
import edu.ntnu.idi.idatt.ikbackend.service.AuthService;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for authorization. Controls services related to logging in and registering
 * users.
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

  private final AuthService authService;
  private final JwtUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;
  private final EmployeeRepository employeeRepository;

  public AuthController(AuthService authService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder,
      EmployeeRepository employeeRepository) {
    this.authService = authService;
    this.jwtUtil = jwtUtil;
    this.passwordEncoder = passwordEncoder;
    this.employeeRepository = employeeRepository;
  }

  /**
   * POST method for logging in. Takes in a login request and when accepted, provides a JWT token to
   * the user.
   *
   * @param loginRequest, information pertaining to the request.
   * @return HTTP response with status code and user.
   */
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    try {
      Employee employee = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
      String token = jwtUtil.generateToken(employee.getEmail());
      return ResponseEntity.ok(Map.of("token", token, "user", employee));
    } catch (RuntimeException e) {
      return ResponseEntity.status(401).body(e.getMessage());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * POST method for registering a new organization and its first employee. Takes a register request
   * and when accepted, creates the organization and first employee, then assigns the user a JWT
   * token and logs them in.
   *
   * @param registerRequest, information pertaining to the request.
   * @return HTTP response with status code and user.
   */
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {

    try {
      Employee employee = authService.register(
          registerRequest.getOrgName(),
          registerRequest.getOrgNr(),
          registerRequest.getEmail(),
          registerRequest.getPassword()
      );
      String token = jwtUtil.generateToken(employee.getEmail());
      return ResponseEntity.ok(Map.of("token", token, "user", employee));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  /**
   * POST method for creating new employees. Takes in information about a new employee and adds them
   * to the database.
   *
   * @param employeeDto, information pertaining to the request.
   * @param token,       the logged in users token.
   * @return
   */
  @PostMapping("/employees")
  public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto,
      @RequestHeader("Authorization") String token) {
    try {
      String email = jwtUtil.extractEmail(token.substring(7));
      Employee currentUser = employeeRepository.findByEmail(email).orElseThrow();

      if (!currentUser.isAdmin()) {
        return ResponseEntity.status(403).body("You are not allowed to perform this operation");
      }

      Employee employee = authService.newEmployee(
          employeeDto.getFirstName(),
          employeeDto.getLastName(),
          employeeDto.getEmail(),
          employeeDto.getPosition(),
          employeeDto.isAdmin(),
          "temp123", // temporary!!
          currentUser.getOrganization()
      );
      return ResponseEntity.ok(employee);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  /**
   * GET method for employees. Retrieves information about the employees within the logged in users
   * organization.
   *
   * @param token, the logged in users token.
   * @return HTTP response with status and employees from the users organization.
   */
  @GetMapping("/employees")
  public ResponseEntity<?> getEmployees(@RequestHeader("Authorization") String token) {

    String email = jwtUtil.extractEmail(token.substring(7));
    Employee currentUser = employeeRepository.findByEmail(email).orElseThrow();

    if (!currentUser.isAdmin()) {
      return ResponseEntity.status(403).body("You do not have permission to access this resource");
    }

    return ResponseEntity.ok(employeeRepository.findByOrganization(currentUser.getOrganization()));
  }
}
