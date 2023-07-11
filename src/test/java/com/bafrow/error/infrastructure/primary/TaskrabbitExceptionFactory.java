package com.bafrow.error.infrastructure.primary;

import com.bafrow.error.domain.TaskrabbitException;

public final class TaskrabbitExceptionFactory {

  private TaskrabbitExceptionFactory() {}

  public static final TaskrabbitException buildEmptyException() {
    return TaskrabbitException.builder(null).build();
  }
}
