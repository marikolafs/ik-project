package edu.ntnu.idi.idatt.ikbackend.repository;

import edu.ntnu.idi.idatt.ikbackend.model.StorageUnit;
import edu.ntnu.idi.idatt.ikbackend.model.TempLog;
import java.time.LocalDate;
import java.util.List;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempLogRepository extends JpaRepository<TempLog, Long> {
  List<TempLog> findByStorageUnit(StorageUnit storageUnit);

  List<TempLog> findByStorageUnitIdAndDateAfter(Long unitId, LocalDate fromDate);
}
