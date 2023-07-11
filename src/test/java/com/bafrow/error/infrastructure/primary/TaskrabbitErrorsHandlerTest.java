package com.bafrow.error.infrastructure.primary;

import static org.mockito.Mockito.*;

import ch.qos.logback.classic.Level;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.MessageSource;
import com.bafrow.LogsSpy;
import com.bafrow.UnitTest;
import com.bafrow.error.domain.TaskrabbitException;
import com.bafrow.error.domain.StandardErrorKey;

@UnitTest
@ExtendWith(LogsSpy.class)
class TaskrabbitErrorsHandlerTest {

  private static final TaskrabbitErrorsHandler handler = new TaskrabbitErrorsHandler(mock(MessageSource.class));

  private final LogsSpy logs;

  public TaskrabbitErrorsHandlerTest(LogsSpy logs) {
    this.logs = logs;
  }

  @Test
  void shouldLogServerErrorAsError() {
    handler.handleTaskrabbitException(
      TaskrabbitException.internalServerError(StandardErrorKey.INTERNAL_SERVER_ERROR).message("Oops").build()
    );

    logs.shouldHave(Level.ERROR, "Oops");
  }

  @Test
  void shouldLogClientErrorAsInfo() {
    handler.handleTaskrabbitException(TaskrabbitException.badRequest(StandardErrorKey.BAD_REQUEST).message("Oops").build());

    logs.shouldHave(Level.INFO, "Oops");
  }
}
