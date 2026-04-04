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

@Entity
@Table(name = "unit")
public class StorageUnit {

  public enum StorageType {
    FRIDGE,
    FREEZER,
    HEATER
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Enumerated(EnumType.STRING)
  private StorageType type;

  private Double minTemp;
  private Double maxTemp;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "org_id")
  private Organization organization;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public StorageType getType() {
    return type;
  }

  public void setType(StorageType type) {
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
