package edu.ntnu.idi.idatt.ikbackend.service;

import edu.ntnu.idi.idatt.ikbackend.model.Organization;
import edu.ntnu.idi.idatt.ikbackend.model.StorageUnit;
import edu.ntnu.idi.idatt.ikbackend.model.StorageUnit.StorageType;
import edu.ntnu.idi.idatt.ikbackend.model.TempLog;
import edu.ntnu.idi.idatt.ikbackend.repository.StorageUnitRepository;
import edu.ntnu.idi.idatt.ikbackend.repository.TempLogRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

/**
 * Service class for managing logic related to storage units and logging their temperatures.
 */
@Service
public class TempLogService {

  private final StorageUnitRepository storageUnitRepository;
  private final TempLogRepository tempLogRepository;

  public TempLogService(StorageUnitRepository storageUnitRepository,
      TempLogRepository tempLogRepository) {
    this.storageUnitRepository = storageUnitRepository;
    this.tempLogRepository = tempLogRepository;
  }

  /**
   * Creates a new storage unit using given information. Sets default min/max temperatures based on
   * the type of unit if no other temperatures are specified.
   *
   * @param name the name of the unit.
   * @param type the type of unit.
   * @param min  the minimum temperature allowed for the unit.
   * @param max  the maximum temperature allowed for the unit.
   * @param org  the organization the unit belongs to.
   * @return the created unit.
   */
  public StorageUnit createUnit(String name, String type, Double min, Double max,
      Organization org) {

    StorageType unitType = StorageType.valueOf(type.toUpperCase());

    if (min == null || max == null) {
      switch (unitType) {
        case FRIDGE -> {
          min = 0.0;
          max = 4.0;
        }
        case FREEZER -> {
          min = -30.0;
          max = -18.0;
        }
        case HEATER -> {
          min = 60.0;
          max = 100.0;
        }
      }
    }

    StorageUnit storageUnit = new StorageUnit();
    storageUnit.setName(name);
    storageUnit.setType(unitType);
    storageUnit.setMinTemp(min);
    storageUnit.setMaxTemp(max);
    storageUnit.setOrganization(org);

    return storageUnitRepository.save(storageUnit);
  }

  /**
   * Creates a new temperature log for a storage unit.
   *
   * @param temp        the temperature of the unit.
   * @param storageUnit the unit the log belongs to.
   * @return the created log.
   */
  public TempLog logTemperature(Double temp, StorageUnit storageUnit) {
    TempLog tempLog = new TempLog();
    tempLog.setTemperature(temp);
    tempLog.setStorageUnit(storageUnit);
    tempLog.setDate(LocalDate.now());
    return tempLogRepository.save(tempLog);
  }
}
