package com.bafrow.taskrabbit.resource;

import com.bafrow.taskrabbit.service.TaskService;
import com.bafrow.taskrabbit.service.dto.TaskDTO;
import com.bafrow.taskrabbit.service.dto.TaskReqDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @Author BaFr0w
 */
@RestController
@Tag(name = "Tasks")
@RequestMapping("/api/v1/tasks")
public class TaskResource {
  private final Logger log = LoggerFactory.getLogger(TaskResource.class);
  private final TaskService taskService;

  public TaskResource(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping
  @Operation(summary = "Add a task to the list")
  @ApiResponse(description = "Task added to the list", responseCode = "201")
  public ResponseEntity<TaskDTO> createTask(@Validated @RequestBody TaskReqDTO taskDTO) {
    TaskDTO createdTask = taskService.createTask(taskDTO);
    return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
  }

  @GetMapping
  @Operation(summary = "Get all tasks")
  @ApiResponse(description = "List of tasks", responseCode = "200")
  public ResponseEntity<List<TaskDTO>> getAllTasks() {
    List<TaskDTO> tasks = taskService.getAllTasks();
    return new ResponseEntity<>(tasks, HttpStatus.OK);
  }

  @GetMapping("/{taskId}")
  @Operation(summary = "Find one task from list")
  @ApiResponse(description = "Task found from the list", responseCode = "200")
  public ResponseEntity<TaskDTO> getTaskById(@PathVariable UUID taskId) {
    TaskDTO taskDTO = taskService.getTaskById(taskId);
    if (taskDTO != null) {
      return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/{taskId}")
  @Operation(summary = "update one task from list")
  @ApiResponse(description = "Task updated into the list", responseCode = "200")
  public ResponseEntity<TaskDTO> updateTask(@PathVariable UUID taskId, @Validated @RequestBody TaskReqDTO updatedTaskDTO) {
    TaskDTO updatedTask = taskService.updateTask(taskId, updatedTaskDTO);
    if (updatedTask != null) {
      return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /*@DeleteMapping("/{taskId}")
  @Operation(summary = "Delete one task from list")
  @ApiResponse(description = "Task deleted from the list", responseCode = "200")
  public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
    taskService.deleteTask(taskId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }*/

  @DeleteMapping("/{taskId}")
  @Operation(summary = "Delete one task from list")
  @ApiResponse(description = "Task deleted from the list", responseCode = "200")
  public ResponseEntity<String> deleteTask(@PathVariable UUID taskId) {
    taskService.deleteTask(taskId);
    return new ResponseEntity<>("Task ID " + taskId + " deleted successfully.", HttpStatus.OK);
  }
}
