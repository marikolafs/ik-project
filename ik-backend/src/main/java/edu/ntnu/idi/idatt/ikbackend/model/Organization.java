package edu.ntnu.idi.idatt.ikbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents an organization. Maps to the "organization" table and stores relevant information
 * about each organization.
 */
@Entity
@Table(name = "organization")
public class Organization {

  /**
   * Primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(name = "org_nr")
  private String orgNr;

  /**
   * One organization can have many tasks.
   */
  @OneToMany(mappedBy = "organization")
  private Set<Task> tasks = new HashSet<>();

  /**
   * One organization can have many employees.
   */
  @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Employee> employees;

  /**
   * Accessor method for an organizations id.
   *
   * @return the organizations id.
   */
  public Long getId() {
    return id;
  }

  /**
   * Mutator method for an organizations id.
   *
   * @param id the id to be set.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Accessor method for the organizations name.
   *
   * @return the organizations name.
   */
  public String getName() {
    return name;
  }

  /**
   * Mutator method for the organizations name.
   *
   * @param name the name to be set.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Accessor method for the organization number.
   *
   * @return the organization number.
   */
  public String getOrgNr() {
    return orgNr;
  }

  /**
   * Mutator method for the organization number.
   *
   * @param orgNr the organization number to be set.
   */
  public void setOrgNr(String orgNr) {
    this.orgNr = orgNr;
  }

  /**
   * Accessor method for tasks belonging to the organization.
   *
   * @return a set of tasks.
   */
  public Set<Task> getTasks() {
    return tasks;
  }

  /**
   * Mutator method for tasks belonging to the organization.
   *
   * @param tasks the set of tasks to be set.
   */
  public void setTasks(Set<Task> tasks) {
    this.tasks = tasks;
  }

  /**
   * Accessor method for employees belonging to the organization.
   *
   * @return a list of employees.
   */
  public List<Employee> getEmployees() {
    return employees;
  }

  /**
   * Mutator method for employees belonging to the organization.
   *
   * @param employees the list of employees to be set.
   */
  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }
}
