package com.bafrow.taskrabbit.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author BaFr0w
 */
@Entity
@Table( name = "task")
public class Task implements Serializable {

  @Serial
  private static final long serialVersionUID = -2324996825722501889L;
  @Id
  @GeneratedValue
  @Column(name = "id")
  private UUID id;
  @Column(name = "title")
  private String title;
  @Column(name = "description")
  private String description;
  @Column(name = "location")
  private String location;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime CreatedAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime UpdatedAt;

  public Task() {
  }

  public Task(UUID id, String title, String description, String location, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.location = location;
    CreatedAt = createdAt;
    UpdatedAt = updatedAt;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public LocalDateTime getCreatedAt() {
    return CreatedAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    CreatedAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return UpdatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    UpdatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Task task = (Task) o;
    return id.equals(task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(location, task.location) && Objects.equals(CreatedAt, task.CreatedAt) && Objects.equals(UpdatedAt, task.UpdatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, location, CreatedAt, UpdatedAt);
  }

  @Override
  public String toString() {
    return "Task{" +
      "id=" + id +
      ", title='" + title + '\'' +
      ", description='" + description + '\'' +
      ", location='" + location + '\'' +
      ", CreatedAt=" + CreatedAt +
      ", UpdatedAt=" + UpdatedAt +
      '}';
  }
}
