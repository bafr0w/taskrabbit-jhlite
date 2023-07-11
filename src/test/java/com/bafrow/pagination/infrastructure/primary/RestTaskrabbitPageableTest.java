package com.bafrow.pagination.infrastructure.primary;

import static com.bafrow.BeanValidationAssertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bafrow.UnitTest;
import com.bafrow.pagination.domain.TaskrabbitPageable;

@UnitTest
class RestTaskrabbitPageableTest {

  @Test
  void shouldConvertToDomain() {
    TaskrabbitPageable pageable = pageable().toPageable();

    assertThat(pageable.page()).isEqualTo(1);
    assertThat(pageable.pageSize()).isEqualTo(15);
  }

  @Test
  void shouldNotValidateWithPageUnderZero() {
    RestTaskrabbitPageable pageable = pageable();
    pageable.setPage(-1);

    assertThatBean(pageable).hasInvalidProperty("page");
  }

  @Test
  void shouldNotValidateWithSizeAtZero() {
    RestTaskrabbitPageable pageable = pageable();
    pageable.setPageSize(0);

    assertThatBean(pageable).hasInvalidProperty("pageSize").withParameter("value", 1L);
  }

  @Test
  void shouldNotValidateWithPageSizeOverHundred() {
    RestTaskrabbitPageable pageable = pageable();
    pageable.setPageSize(101);

    assertThatBean(pageable).hasInvalidProperty("pageSize");
  }

  private RestTaskrabbitPageable pageable() {
    return new RestTaskrabbitPageable(1, 15);
  }
}
