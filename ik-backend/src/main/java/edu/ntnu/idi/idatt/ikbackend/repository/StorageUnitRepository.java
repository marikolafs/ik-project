package edu.ntnu.idi.idatt.ikbackend.repository;

import edu.ntnu.idi.idatt.ikbackend.model.StorageUnit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageUnitRepository extends JpaRepository<StorageUnit, Long> {
  List<StorageUnit> findByOrganizationId(Long orgId);
}
