package com.bafrow.taskrabbit.service;

/**
 * @Author BaFr0w
 */
import com.bafrow.taskrabbit.domain.Tasker;
import com.bafrow.taskrabbit.repository.TaskerRepository;
import com.bafrow.taskrabbit.service.dto.TaskerDTO;
import com.bafrow.taskrabbit.service.dto.TaskerReqDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskerService {
  private final TaskerRepository taskerRepository;

  public TaskerService(TaskerRepository taskerRepository) {
    this.taskerRepository = taskerRepository;
  }

  @Transactional
  public TaskerDTO createTasker(TaskerReqDTO taskerDTO) {
    Tasker tasker = new Tasker(
      UUID.randomUUID(),
      taskerDTO.name(),
      taskerDTO.email(),
      taskerDTO.phoneNumber(),
      taskerDTO.location(),
      LocalDateTime.now(),
      LocalDateTime.now()
    );

    Tasker createdTasker = taskerRepository.save(tasker);

    return convertToDTO(createdTasker);
  }

  public TaskerDTO getTaskerById(UUID taskerId) {
    Optional<Tasker> taskerOptional = taskerRepository.findById(taskerId);
    return taskerOptional.map(this::convertToDTO).orElse(null);
  }

  public List<TaskerDTO> getAllTaskers() {
    List<Tasker> taskers = taskerRepository.findAll();
    return taskers.stream()
      .map(this::convertToDTO)
      .collect(Collectors.toList());
  }

  @Transactional
  public TaskerDTO updateTasker(UUID taskerId, TaskerReqDTO updatedTaskerDTO) {
    Optional<Tasker> taskerOptional = taskerRepository.findById(taskerId);
    if (taskerOptional.isPresent()) {
      Tasker tasker = taskerOptional.get();
      tasker.setName(updatedTaskerDTO.name());
      tasker.setEmail(updatedTaskerDTO.email());
      tasker.setPhoneNumber(updatedTaskerDTO.phoneNumber());
      tasker.setLocation(updatedTaskerDTO.location());
      tasker.setUpdatedAt(LocalDateTime.now());

      Tasker updatedTasker = taskerRepository.save(tasker);

      return convertToDTO(updatedTasker);
    } else {
      return null;
    }
  }

  @Transactional
  public void deleteTasker(UUID taskerId) {
    taskerRepository.deleteById(taskerId);
  }

  private TaskerDTO convertToDTO(Tasker tasker) {
    return new TaskerDTO(
      tasker.getId(),
      tasker.getName(),
      tasker.getEmail(),
      tasker.getPhoneNumber(),
      tasker.getLocation(),
      tasker.getCreatedAt(),
      tasker.getUpdatedAt()
    );
  }
}

