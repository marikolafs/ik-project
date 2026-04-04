package edu.ntnu.idi.idatt.ikbackend.dto;

public class TemperatureDto {

  private Double temperature;
  private Long unitId;

  public Double getTemperature() {
    return temperature;
  }

  public void setTemperature(Double temperature) {
    this.temperature = temperature;
  }

  public Long getUnitId() {
    return unitId;
  }

  public void setUnit(Long unitId) {
    this.unitId = unitId;
  }
}
