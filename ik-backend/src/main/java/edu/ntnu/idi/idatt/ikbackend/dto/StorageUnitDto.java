package edu.ntnu.idi.idatt.ikbackend.dto;

import edu.ntnu.idi.idatt.ikbackend.model.Organization;

public class StorageUnitDto {

  private String name;
  private String type;
  private Double minTemp;
  private Double maxTemp;
  private Organization organization;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Double getMinTemp() {
    return minTemp;
  }

  public void setMinTemp(Double minTemp) {
    this.minTemp = minTemp;
  }

  public Double getMaxTemp() {
    return maxTemp;
  }

  public void setMaxTemp(Double maxTemp) {
    this.maxTemp = maxTemp;
  }

  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

}
