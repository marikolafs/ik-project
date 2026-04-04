package edu.ntnu.idi.idatt.ikbackend.repository;

import edu.ntnu.idi.idatt.ikbackend.model.TempLog;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing temperature log entities. Extends JpaRepository to provide
 * query methods for accessing temperature logs by specific attributes.
 */
public interface TempLogRepository extends JpaRepository<TempLog, Long> {

  /**
   * Retrieves all temperature logs belonging to a certain storage unit, logged within a given
   * timeframe.
   *
   * @param unitId   the id of the unit whose logs are being retrieved.
   * @param fromDate the earliest date for a log that should be retrieved.
   * @return a list of temperature logs.
   */
  List<TempLog> findByStorageUnitIdAndDateAfter(Long unitId, LocalDate fromDate);
}
