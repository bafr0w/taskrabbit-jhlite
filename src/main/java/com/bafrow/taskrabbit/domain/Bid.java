package com.bafrow.taskrabbit.domain;

/**
 * @Author BaFr0w
 */
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "bid")
public class Bid implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "task_id", nullable = false)
  private Task task;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tasker_id", nullable = false)
  private Tasker tasker;

  @Column(name = "amount", nullable = false)
  private BigDecimal amount;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  public Bid() {
  }

  public Bid(UUID id, Task task, Tasker tasker, BigDecimal amount, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.task = task;
    this.tasker = tasker;
    this.amount = amount;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  public Tasker getTasker() {
    return tasker;
  }

  public void setTasker(Tasker tasker) {
    this.tasker = tasker;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Bid bid = (Bid) o;
    return id.equals(bid.id) && Objects.equals(task, bid.task) && Objects.equals(tasker, bid.tasker) && Objects.equals(amount, bid.amount) && Objects.equals(createdAt, bid.createdAt) && Objects.equals(updatedAt, bid.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, task, tasker, amount, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    return "Bid{" +
      "id=" + id +
      ", task=" + task +
      ", tasker=" + tasker +
      ", amount=" + amount +
      ", createdAt=" + createdAt +
      ", updatedAt=" + updatedAt +
      '}';
  }
}

