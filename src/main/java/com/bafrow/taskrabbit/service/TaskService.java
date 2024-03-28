package com.bafrow.taskrabbit.service;

import com.bafrow.taskrabbit.domain.Task;
import com.bafrow.taskrabbit.repository.TaskRepository;
import com.bafrow.taskrabbit.service.dto.TaskDTO;
import com.bafrow.taskrabbit.service.dto.TaskReqDTO;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author BaFr0w
 */
@Service
public class TaskService {
  private final Logger log = LoggerFactory.getLogger(TaskService.class);
  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Transactional
  public TaskDTO createTask(TaskReqDTO taskDTO) {
    Task task = new Task(
      UUID.randomUUID(),
      taskDTO.title(),
      taskDTO.description(),
      taskDTO.location(),
      LocalDateTime.now(),
      LocalDateTime.now()
    );

    Task createdTask = taskRepository.save(task);

    return convertToDTO(createdTask);
  }

  @Transactional
  public TaskDTO getTaskById(UUID taskId) {
    Optional<Task> taskOptional = taskRepository.findById(taskId);
    return taskOptional.map(this::convertToDTO).orElse(null);
  }

  @Transactional
  public TaskDTO updateTask(UUID taskId, TaskReqDTO updatedTaskDTO) {
    Optional<Task> taskOptional = taskRepository.findById(taskId);
    if (taskOptional.isPresent()) {
      Task task = taskOptional.get();
      task.setTitle(updatedTaskDTO.title());
      task.setDescription(updatedTaskDTO.description());
      task.setLocation(updatedTaskDTO.location());
      task.setUpdatedAt(LocalDateTime.now());

      Task updatedTask = taskRepository.save(task);

      return convertToDTO(updatedTask);
    } else {
      return null;
    }
  }

  @Transactional
  public List<TaskDTO> getAllTasks() {
    List<Task> tasks = taskRepository.findAll();
    return tasks.stream()
      .map(this::convertToDTO)
      .collect(Collectors.toList());
  }

  @Transactional
  public void deleteTask(UUID taskId) {
    taskRepository.deleteById(taskId);
  }

  private TaskDTO convertToDTO(Task task) {
    return new TaskDTO(
      task.getId(),
      task.getTitle(),
      task.getDescription(),
      task.getLocation(),
      task.getCreatedAt(),
      task.getUpdatedAt()
    );
  }
}
