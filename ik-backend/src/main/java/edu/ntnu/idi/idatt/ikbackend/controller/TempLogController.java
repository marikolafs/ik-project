package edu.ntnu.idi.idatt.ikbackend.controller;

import edu.ntnu.idi.idatt.ikbackend.dto.StorageUnitDto;
import edu.ntnu.idi.idatt.ikbackend.dto.TemperatureDto;
import edu.ntnu.idi.idatt.ikbackend.model.Employee;
import edu.ntnu.idi.idatt.ikbackend.model.Organization;
import edu.ntnu.idi.idatt.ikbackend.model.StorageUnit;
import edu.ntnu.idi.idatt.ikbackend.model.Task;
import edu.ntnu.idi.idatt.ikbackend.repository.EmployeeRepository;
import edu.ntnu.idi.idatt.ikbackend.repository.StorageUnitRepository;
import edu.ntnu.idi.idatt.ikbackend.repository.TempLogRepository;
import edu.ntnu.idi.idatt.ikbackend.security.JwtUtil;
import edu.ntnu.idi.idatt.ikbackend.service.TempLogService;
import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class TempLogController {

  private final StorageUnitRepository storageUnitRepository;
  private final TempLogRepository tempLogRepository;
  private EmployeeRepository employeeRepository;
  private TempLogService tempLogService;
  private final JwtUtil jwtUtil;

  public TempLogController(StorageUnitRepository storageUnitRepository,
      TempLogRepository tempLogRepository, JwtUtil jwtUtil) {
    this.storageUnitRepository = storageUnitRepository;
    this.tempLogRepository = tempLogRepository;
    this.jwtUtil = jwtUtil;
  }

  private Employee getCurrentUser(String token) {
    String email = jwtUtil.extractEmail(token.substring(7));
    return employeeRepository.findByEmail(email).orElseThrow();
  }

  @PostMapping("/unit")
  public ResponseEntity<?> createTask(@RequestBody StorageUnitDto unitDto, @RequestHeader("Authorization") String token) {

    Employee currentUser = getCurrentUser(token);

    if (!currentUser.isAdmin()) {
      return ResponseEntity.status(403).body("You are not allowed to perform this operation");
    }

    StorageUnit unit = tempLogService.createUnit(unitDto.getName(), unitDto.getType(),
        unitDto.getMinTemp(), unitDto.getMaxTemp(), currentUser.getOrganization());

    return ResponseEntity.ok(unit);
  }

  @GetMapping("/unit")
  public ResponseEntity<?> getAllUnits(@RequestHeader("Authorization") String token) {

    Employee currentUser = getCurrentUser(token);

    return ResponseEntity.ok(storageUnitRepository.findByOrganizationId(currentUser.getOrganization().getId()));
  }

  @PostMapping("/temperature-logs")
  public ResponseEntity<?> logTemperature(@RequestBody TemperatureDto tempDto, @RequestHeader("Authorization") String token) {

    Employee currentUser = getCurrentUser(token);

    StorageUnit unit = storageUnitRepository.findById(tempDto.getUnitId()).orElseThrow();

    if (!unit.getOrganization().getId().equals(currentUser.getOrganization().getId())) {
      return ResponseEntity.status(403).body("You are not allowed to perform this operation");
    }

    return ResponseEntity.ok(tempLogService.logTemperature(tempDto.getTemperature(), unit));
  }

  @GetMapping("/temperature-logs/{unitId}")
  public ResponseEntity<?> getLogs(@PathVariable("unitId") Long unitId, @RequestHeader("Authorization") String token) {

    Employee currentUser = getCurrentUser(token);

    StorageUnit unit = storageUnitRepository.findById(unitId).orElseThrow();

    if (!unit.getOrganization().getId().equals(currentUser.getOrganization().getId())) {
      return ResponseEntity.status(403).body("You are not allowed to perform this operation");
    }

    LocalDate fromDate = LocalDate.now().minusDays(30);

    return ResponseEntity.ok(tempLogRepository.findByStorageUnitIdAndDateAfter(unitId, fromDate));
  }
}
