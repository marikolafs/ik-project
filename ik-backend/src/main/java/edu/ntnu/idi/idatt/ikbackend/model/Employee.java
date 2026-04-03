package edu.ntnu.idi.idatt.ikbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents an employee within the organization. Maps to the "employee" table and stores relevant
 * information about each employee.
 */
@Entity
public class Employee {

  /**
   * Primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employee_id")
  private Long id;

  private String firstName;

  private String lastName;

  private String position;

  private String email;

  private String password;

  private boolean admin;

  /**
   * Sets the "org_id" attribute as a foreign key such that many employees can belong to one organization.
   */
  @ManyToOne
  @JoinColumn(name = "org_id")
  private Organization organization;

  /**
   * Accessor method for the employees id.
   *
   * @return the employees id.
   */
  public Long getId() {
    return id;
  }

  /**
   * Mutator method for the employees id.
   *
   * @param id, the id to be set.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Accessor method for the employees first name.
   *
   * @return the employees first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Mutator method for the employees first name.
   *
   * @param firstName, the name to be set.
   */
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

  /**
   * Mutator method for the employees last name.
   *
   * @param lastName, the name to be set.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Accessor method for the employees job position.
   *
   * @return the employees job position.
   */
  public String getPosition() {
    return position;
  }

  /**
   * Mutator method for the employees job position.
   *
   * @param position, the job position to be set.
   */
  public void setPosition(String position) {
    this.position = position;
  }

  /**
   * Accessor method for the employees email address.
   *
   * @return the employees email address.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Mutator method for the employees email address.
   *
   * @param email, the email address to be set.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Accessor method for the employees password.
   *
   * @return the employees password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Mutator method for the employees password.
   *
   * @param password, the password to be set.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Accessor method that checks whether the employee is set as an administrator.
   *
   * @return true/false whether or not the employee is an administrator.
   */
  public boolean isAdmin() {
    return admin;
  }

  /**
   * Mutator method for employees administrator status.
   *
   * @param admin, the administrator status to be set.
   */
  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  /**
   * Accessor method for the organization the employee belongs to.
   *
   * @return the organization the employee belongs to.
   */
  public Organization getOrganization() {
    return organization;
  }

  /**
   * Mutator method for the organization the employee belongs to.
   *
   * @param organization, the organization the employee should be set to belong to.
   */
  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

}
