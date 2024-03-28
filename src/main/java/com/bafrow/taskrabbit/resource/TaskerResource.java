package com.bafrow.taskrabbit.resource;

/**
 * @Author BaFr0w
 */
import com.bafrow.taskrabbit.service.TaskerService;
import com.bafrow.taskrabbit.service.dto.TaskerDTO;
import com.bafrow.taskrabbit.service.dto.TaskerReqDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Taskers")
@RequestMapping("/api/v1/taskers")
public class TaskerResource {
  private final TaskerService taskerService;

  public TaskerResource(TaskerService taskerService) {
    this.taskerService = taskerService;
  }

  @PostMapping
  @Operation(summary = "Add a tasker to the list")
  @ApiResponse(description = "Tasker added to the list", responseCode = "201")
  public ResponseEntity<TaskerDTO> createTasker(@Validated @RequestBody TaskerReqDTO taskerDTO) {
    TaskerDTO createdTasker = taskerService.createTasker(taskerDTO);
    return new ResponseEntity<>(createdTasker, HttpStatus.CREATED);
  }

  @GetMapping("/{taskerId}")
  @Operation(summary = "Find one tasker from list")
  @ApiResponse(description = "Tasker found from the list", responseCode = "200")
  public ResponseEntity<TaskerDTO> getTaskerById(@PathVariable UUID taskerId) {
    TaskerDTO taskerDTO = taskerService.getTaskerById(taskerId);
    if (taskerDTO != null) {
      return new ResponseEntity<>(taskerDTO, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping
  @Operation(summary = "Get all taskers")
  @ApiResponse(description = "List of taskers", responseCode = "200")
  public ResponseEntity<List<TaskerDTO>> getAllTaskers() {
    List<TaskerDTO> taskers = taskerService.getAllTaskers();
    return new ResponseEntity<>(taskers, HttpStatus.OK);
  }

  @PutMapping("/{taskerId}")
  @Operation(summary = "update one tasker from list")
  @ApiResponse(description = "Tasker updated into the list", responseCode = "200")
  public ResponseEntity<TaskerDTO> updateTasker(@PathVariable UUID taskerId, @Validated @RequestBody TaskerReqDTO updatedTaskerDTO) {
    TaskerDTO updatedTasker = taskerService.updateTasker(taskerId, updatedTaskerDTO);
    if (updatedTasker != null) {
      return new ResponseEntity<>(updatedTasker, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{taskerId}")
  @Operation(summary = "Delete one tasker from list")
  @ApiResponse(description = "Tasker deleted from the list", responseCode = "200")
  public ResponseEntity<Void> deleteTasker(@PathVariable UUID taskerId) {
    taskerService.deleteTasker(taskerId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

