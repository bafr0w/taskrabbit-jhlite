package com.bafrow.error.infrastructure.primary;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bafrow.common.domain.Enums;
import com.bafrow.error.domain.ErrorKey;
import com.bafrow.error.domain.TaskrabbitException;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 1000)
class TaskrabbitErrorsHandler {

  private static final Logger log = LoggerFactory.getLogger(TaskrabbitErrorsHandler.class);
  private static final String MESSAGES_PREFIX = "error.";

  private final MessageSource messages;

  public TaskrabbitErrorsHandler(@Qualifier("applicationErrorMessageSource") MessageSource messages) {
    Locale.setDefault(Locale.ENGLISH);

    this.messages = messages;
  }

  @ExceptionHandler(TaskrabbitException.class)
  ProblemDetail handleTaskrabbitException(TaskrabbitException exception) {
    HttpStatus status = Enums.map(exception.status(), HttpStatus.class);
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, buildDetail(exception));

    problem.setTitle(getMessage(exception.key(), "title"));
    problem.setProperty("key", exception.key().get());

    logException(exception, status);

    return problem;
  }

  private String buildDetail(TaskrabbitException exception) {
    String messageTemplate = getMessage(exception.key(), "detail");

    return ArgumentsReplacer.replaceParameters(messageTemplate, exception.parameters());
  }

  private String getMessage(ErrorKey key, String suffix) {
    return messages.getMessage(MESSAGES_PREFIX + key.get() + "." + suffix, null, locale());
  }

  private Locale locale() {
    return LocaleContextHolder.getLocale();
  }

  private void logException(TaskrabbitException exception, HttpStatus status) {
    if (status.is4xxClientError()) {
      log.info(exception.getMessage(), exception);
    } else {
      log.error(exception.getMessage(), exception);
    }
  }
}
