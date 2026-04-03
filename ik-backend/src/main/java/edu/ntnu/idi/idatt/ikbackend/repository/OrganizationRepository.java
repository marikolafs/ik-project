package edu.ntnu.idi.idatt.ikbackend.repository;

import edu.ntnu.idi.idatt.ikbackend.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing organization entities. Extends JpaRepository to provide query
 * methods for accessing organizations by specific attributes.
 */
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
