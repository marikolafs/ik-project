package edu.ntnu.idi.idatt.ikbackend.dto;

/**
 * DTO class for temperature logs. Specifies attributes related to a temperature logging request.
 */
public class TemperatureDto {

  private Double temperature;
  private Long unitId;

  /**
   * Accessor method for the units temperature.
   *
   * @return the units temperature.
   */
  public Double getTemperature() {
    return temperature;
  }

  /**
   * Mutator method for the units temperature.
   *
   * @param temperature the temperature to be set.
   */
  public void setTemperature(Double temperature) {
    this.temperature = temperature;
  }

  /**
   * Accessor method for the id of the unit the log belongs to.
   *
   * @return the id of the unit the log belongs to.
   */
  public Long getUnitId() {
    return unitId;
  }

  /**
   * Mutator method for the id of the unit the log belongs to.
   *
   * @param unitId the id of the unit to be set.
   */
  public void setUnitId(Long unitId) {
    this.unitId = unitId;
  }
}
