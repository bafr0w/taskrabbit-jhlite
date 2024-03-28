package com.bafrow.taskrabbit.service.dto;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author BaFr0w
 */
public record TaskDTO(
  UUID id,
  String title,
  String description,
  String location,
  LocalDateTime CreatedAt,
  LocalDateTime UpdatedAt
) {
}
