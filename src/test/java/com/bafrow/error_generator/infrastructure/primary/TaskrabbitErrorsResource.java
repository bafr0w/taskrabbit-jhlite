package com.bafrow.error_generator.infrastructure.primary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bafrow.error.domain.TaskrabbitException;
import com.bafrow.error.domain.StandardErrorKey;

@RestController
@RequestMapping("/api/errors")
class TaskrabbitErrorsResource {

  @GetMapping("bad-request")
  void getBadRequest() {
    throw TaskrabbitException.badRequest(StandardErrorKey.BAD_REQUEST).addParameter("code", "400").build();
  }
}
