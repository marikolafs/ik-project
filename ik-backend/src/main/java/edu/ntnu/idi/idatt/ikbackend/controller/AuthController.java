package edu.ntnu.idi.idatt.ikbackend.controller;


import edu.ntnu.idi.idatt.ikbackend.dto.LoginRequest;
import edu.ntnu.idi.idatt.ikbackend.dto.RegisterRequest;
import edu.ntnu.idi.idatt.ikbackend.model.Employee;
import edu.ntnu.idi.idatt.ikbackend.security.JwtUtil;
import edu.ntnu.idi.idatt.ikbackend.service.AuthService;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

  private final AuthService authService;
  private final JwtUtil jwtUtil;

  public AuthController(AuthService authService,  JwtUtil jwtUtil) {
    this.authService = authService;
    this.jwtUtil = jwtUtil;
  }

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
}
