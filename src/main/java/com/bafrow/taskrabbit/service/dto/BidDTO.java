package com.bafrow.taskrabbit.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author BaFr0w
 */
public record BidDTO(UUID id,
                     UUID taskId,
                     UUID taskerId,
                     BigDecimal amount,
                     LocalDateTime createdAt,
                     LocalDateTime updatedAt) {
}

