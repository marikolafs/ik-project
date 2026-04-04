package edu.ntnu.idi.idatt.ikbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a storage unit. Maps to the "unit" table and stores relevant information about each
 * storage unit.
 */
@Entity
@Table(name = "unit")
public class StorageUnit {

  public enum StorageType {
    FRIDGE,
    FREEZER,
    HEATER
  }

  /**
   * Primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  /**
   * Type can be "fridge", "freezer", or "heater".
   */
  @Enumerated(EnumType.STRING)
  private StorageType type;

  private Double minTemp;
  private Double maxTemp;

  /**
   * Sets the "org_id" attribute as a foreign key such that many units can belong to one
   * organization.
   */
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "org_id")
  private Organization organization;

  /**
   * Accessor method for the units id.
   *
   * @return the units id.
   */
  public Long getId() {
    return id;
  }

  /**
   * Mutator method for the units id.
   *
   * @param id the id to be set.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Accessor method for the units name.
   *
   * @return the units name.
   */
  public String getName() {
    return name;
  }

  /**
   * Mutator method for the units name.
   *
   * @param name the name to be set.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Accessor method for the units type.
   *
   * @return the units type.
   */
  public StorageType getType() {
    return type;
  }

  /**
   * Mutator method for the units type.
   *
   * @param type the type to be set.
   */
  public void setType(StorageType type) {
    this.type = type;
  }

  /**
   * Accessor method for the units minimum expected temperature.
   *
   * @return the units minimum temperature.
   */
  public Double getMinTemp() {
    return minTemp;
  }

  /**
   * Mutator method for the units minimum expected temperature.
   *
   * @param minTemp the temperature to be set.
   */
  public void setMinTemp(Double minTemp) {
    this.minTemp = minTemp;
  }

  /**
   * Accessor method for the units maximum expected temperature.
   *
   * @return the units maximum temperature.
   */
  public Double getMaxTemp() {
    return maxTemp;
  }

  /**
   * Mutator method for the units maximum expected temperature.
   *
   * @param maxTemp the temperature to be set.
   */
  public void setMaxTemp(Double maxTemp) {
    this.maxTemp = maxTemp;
  }

  /**
   * Accessor method for the organization the unit belongs to.
   *
   * @return the organization the unit belongs to.
   */
  public Organization getOrganization() {
    return organization;
  }

  /**
   * Mutator method for the organization the unit belongs to.
   *
   * @param organization the organization the unit should be set to belong to.
   */
  public void setOrganization(Organization organization) {
    this.organization = organization;
  }
}
