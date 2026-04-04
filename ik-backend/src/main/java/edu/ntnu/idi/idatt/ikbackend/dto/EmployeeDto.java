package edu.ntnu.idi.idatt.ikbackend.dto;

/**
 * DTO class for employees. Specifies attributes related to an employee request.
 */
public class EmployeeDto {

  private String firstName;
  private String lastName;
  private String email;
  private String position;
  private boolean admin;

  /**
   * Accessor method for the employees first name.
   * @return the employees first name.
   */
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Accessor method for the employees last name.
   *
   * @return the employees last name.
   */
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Accessor method for the employees email address.
   *
   * @return the employees email address.
   */
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Accessor method for the employees job position.
   *
   * @return the employees job position.
   */
  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  /**
   * Accessor method for the employees administrator status.
   *
   * @return the employees administrator status.
   */
  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }
}
