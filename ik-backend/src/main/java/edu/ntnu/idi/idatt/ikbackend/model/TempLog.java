package edu.ntnu.idi.idatt.ikbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 * Represents a logged temperature of a storage unit. Maps to the "temperature" table and stores
 * relevant information about each logged temperature.
 */
@Entity
@Table(name = "temperature")
public class TempLog {

  /**
   * Primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double temperature;
  private LocalDate date;

  /**
   * Sets the "unit_id" attribute as a foreign key such that many temperatures can belong to one
   * storage unit.
   */
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "unit_id")
  private StorageUnit storageUnit;

  /**
   * Accessor method for a logs id.
   *
   * @return the logs id.
   */
  public Long getId() {
    return id;
  }

  /**
   * Mutator method for a logs id.
   *
   * @param id the id to be set.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Accessor method for the temperature to be logged.
   *
   * @return the temperature of a storage unit.
   */
  public Double getTemperature() {
    return temperature;
  }

  /**
   * Mutator method for the temperature to be logged.
   *
   * @param temperature the temperature to be set.
   */
  public void setTemperature(Double temperature) {
    this.temperature = temperature;
  }

  /**
   * Accessor method for the date of the log.
   *
   * @return the date of the log.
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * Mutator method for the date of the log.
   *
   * @param date the date to be set.
   */
  public void setDate(LocalDate date) {
    this.date = date;
  }

  /**
   * Accessor method for the storage unit the log belongs to.
   *
   * @return the storage unit the log belongs to.
   */
  public StorageUnit getStorageUnit() {
    return storageUnit;
  }

  /**
   * Mutator method for the storage unit the log belongs to.
   *
   * @param storageUnit the unit the log should be set to belong to.
   */
  public void setStorageUnit(StorageUnit storageUnit) {
    this.storageUnit = storageUnit;
  }
}
