package com.bafrow.taskrabbit.service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author BaFr0w
 */
public record TaskerReqDTO(
                           String name,
                           String email,
                           String phoneNumber,
                           String location) {
}

