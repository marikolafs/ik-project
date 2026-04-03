package edu.ntnu.idi.idatt.ikbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents a task. Maps to the "task" table and stores relevant information about each task.
 */
@Entity
public class Task {

  /**
   * Primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "task_id")
  private Long id;

  private String title;

  private String description;

  private Boolean completed;

  /**
   * Sets the "org_id" attribute as a foreign key such that many employees can belong to one
   * organization.
   */
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "org_id")
  private Organization organization;

  /**
   * Accessor method for the tasks id.
   *
   * @return the tasks id.
   */
  public Long getId() {
    return id;
  }

  /**
   * Mutator method for the tasks id.
   *
   * @param id the id to be set.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Accessor method for the tasks title.
   *
   * @return the tasks title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Mutator method for the tasks title.
   *
   * @param title the title to be set.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Accessor method for the tasks description.
   *
   * @return the tasks description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Mutator method for the tasks description.
   *
   * @param description the description to be set.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Accessor method for whether the task has been completed.
   *
   * @return true/false whether the task has been completed.
   */
  public Boolean getCompleted() {
    return completed;
  }

  /**
   * Mutator method to update a tasks completion status.
   *
   * @param completed the completion status to be set.
   */
  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  /**
   * Accessor method for the organization the task belongs to.
   *
   * @return the organization the task belongs to.
   */
  public Organization getOrganization() {
    return organization;
  }

  /**
   * Mutator method for the organization the task belongs to.
   *
   * @param organization the organization the task should be set to belong to.
   */
  public void setOrganization(Organization organization) {
    this.organization = organization;
  }
}
