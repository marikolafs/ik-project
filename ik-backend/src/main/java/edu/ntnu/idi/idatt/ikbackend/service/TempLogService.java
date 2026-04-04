package edu.ntnu.idi.idatt.ikbackend.service;

import edu.ntnu.idi.idatt.ikbackend.model.Organization;
import edu.ntnu.idi.idatt.ikbackend.model.StorageUnit;
import edu.ntnu.idi.idatt.ikbackend.model.StorageUnit.StorageType;
import edu.ntnu.idi.idatt.ikbackend.model.TempLog;
import edu.ntnu.idi.idatt.ikbackend.repository.StorageUnitRepository;
import edu.ntnu.idi.idatt.ikbackend.repository.TempLogRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class TempLogService {

  private final StorageUnitRepository storageUnitRepository;
  private final TempLogRepository tempLogRepository;

  public TempLogService(StorageUnitRepository storageUnitRepository,
      TempLogRepository tempLogRepository) {
    this.storageUnitRepository = storageUnitRepository;
    this.tempLogRepository = tempLogRepository;
  }

  public StorageUnit createUnit(String name, String type, Double min, Double max, Organization org) {

    StorageType unitType = StorageType.valueOf(type.toUpperCase());

    if (min == null || max == null) {
      switch (unitType) {
        case FRIDGE -> {
          min = 0.0;
          max = 4.0;
        }
        case FREEZER ->  {
          min = -30.0;
          max = -18.0;
        }
        case HEATER ->   {
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

  public TempLog logTemperature(Double temp, StorageUnit storageUnit) {
    TempLog tempLog = new TempLog();
    tempLog.setTemperature(temp);
    tempLog.setStorageUnit(storageUnit);
    tempLog.setDate(LocalDate.now());
    return tempLogRepository.save(tempLog);
  }
}
