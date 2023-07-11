package com.bafrow.error.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.Test;
import com.bafrow.UnitTest;
import com.bafrow.error.infrastructure.primary.TaskrabbitExceptionFactory;

@UnitTest
class TaskrabbitExceptionTest {

  @Test
  void shouldGetMinimalTaskrabbitExceptionFromDomain() {
    TaskrabbitException exception = TaskrabbitException.builder(null).build();

    assertThat(exception.key()).isEqualTo(StandardErrorKey.INTERNAL_SERVER_ERROR);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
    assertThat(exception.getMessage()).isEqualTo("An error occured");
    assertThat(exception.getCause()).isNull();
    assertThat(exception.parameters()).isEmpty();
  }

  @Test
  void shouldGetMinimalTaskrabbitExceptionFromPrimary() {
    TaskrabbitException exception = TaskrabbitExceptionFactory.buildEmptyException();

    assertThat(exception.key()).isEqualTo(StandardErrorKey.INTERNAL_SERVER_ERROR);
    assertThat(exception.status()).isEqualTo(ErrorStatus.BAD_REQUEST);
    assertThat(exception.getMessage()).isEqualTo("An error occured");
    assertThat(exception.getCause()).isNull();
    assertThat(exception.parameters()).isEmpty();
  }

  @Test
  void shouldGetFullTaskrabbitException() {
    RuntimeException cause = new RuntimeException();
    TaskrabbitException exception = TaskrabbitException
      .builder(StandardErrorKey.BAD_REQUEST)
      .message("This is an error")
      .cause(cause)
      .addParameter("parameter", "value")
      .addParameters(Map.of("key", "value"))
      .status(ErrorStatus.BAD_REQUEST)
      .build();

    assertThat(exception.key()).isEqualTo(StandardErrorKey.BAD_REQUEST);
    assertThat(exception.status()).isEqualTo(ErrorStatus.BAD_REQUEST);
    assertThat(exception.getMessage()).isEqualTo("This is an error");
    assertThat(exception.getCause()).isEqualTo(cause);
    assertThat(exception.parameters()).containsOnly(entry("parameter", "value"), entry("key", "value"));
  }
  
  @Test
  void shouldGetTechnicalErrorExceptionFromMessage() {
    TaskrabbitException exception = TaskrabbitException.technicalError("This is a problem");
    
    assertThat(exception.getMessage()).isEqualTo("This is a problem");
    assertThat(exception.key()).isEqualTo(StandardErrorKey.INTERNAL_SERVER_ERROR);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
  }

  @Test
  void shouldGetTechnicalErrorException() {
    RuntimeException cause = new RuntimeException();
    TaskrabbitException exception = TaskrabbitException.technicalError("This is a problem", cause);

    assertThat(exception.getMessage()).isEqualTo("This is a problem");
    assertThat(exception.key()).isEqualTo(StandardErrorKey.INTERNAL_SERVER_ERROR);
    assertThat(exception.getCause()).isEqualTo(cause);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
  }
  
  @Test
  void shouldGetInternalServerErrorShortcut() {
    TaskrabbitException exception = TaskrabbitException.internalServerError(StandardErrorKey.INTERNAL_SERVER_ERROR).build();
    
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
  }
  
  @Test
  void shouldGetBadRequestShortcut() {
    TaskrabbitException exception = TaskrabbitException.badRequest(StandardErrorKey.BAD_REQUEST).build();
    
    assertThat(exception.status()).isEqualTo(ErrorStatus.BAD_REQUEST);
  }
}
