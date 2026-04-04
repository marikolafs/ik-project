package edu.ntnu.idi.idatt.ikbackend.dto;

import edu.ntnu.idi.idatt.ikbackend.model.Organization;

/**
 * DTO class for storage units. Specifies attributes related to a storage unit request.
 */
public class StorageUnitDto {

  private String name;
  private String type;
  private Double minTemp;
  private Double maxTemp;
  private Organization organization;

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
  public String getType() {
    return type;
  }

  /**
   * Mutator method for the units type.
   *
   * @param type the type to be set.
   */
  public void setType(String type) {
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
   * @param organization the organization the unit should be set ot belong to.
   */
  public void setOrganization(Organization organization) {
    this.organization = organization;
  }
}
