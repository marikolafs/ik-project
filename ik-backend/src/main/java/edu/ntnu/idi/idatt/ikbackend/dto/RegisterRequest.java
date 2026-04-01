package edu.ntnu.idi.idatt.ikbackend.dto;

public class RegisterRequest {

  private String orgName;
  private String orgNr;
  private String email;
  private String password;

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getOrgNr() {
    return orgNr;
  }

  public void setOrgNr(String orgNr) {
    this.orgNr = orgNr;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
