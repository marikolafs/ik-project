package edu.ntnu.idi.idatt.ikbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "organization")
public class Organization {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(name = "org_nr")
  private String orgNr;

  @OneToMany(mappedBy = "organization")
  private Set<Task> tasks = new HashSet<>();

  @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Employee> employees;

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

  public String getOrgNr() {
    return orgNr;
  }

  public void setOrgNr(String orgNr) {
    this.orgNr = orgNr;
  }

  public Set<Task> getTasks() {
    return tasks;
  }

  public void setTasks(Set<Task> tasks) {
    this.tasks = tasks;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }
}
