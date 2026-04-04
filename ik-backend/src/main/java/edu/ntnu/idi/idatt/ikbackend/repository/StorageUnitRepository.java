package edu.ntnu.idi.idatt.ikbackend.repository;

import edu.ntnu.idi.idatt.ikbackend.model.StorageUnit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing storage unit entities. Extends JpaRepository to provide query
 * methods for accessing storage units by specific attributes.
 */
public interface StorageUnitRepository extends JpaRepository<StorageUnit, Long> {

  /**
   * Retrieves all storage units belonging to an organization.
   *
   * @param orgId the id of the organization whose units are being retrieved.
   * @return a list of units.
   */
  List<StorageUnit> findByOrganizationId(Long orgId);
}
