package com.bafrow.pagination.infrastructure.primary;

import static org.assertj.core.api.Assertions.*;
import static com.bafrow.pagination.domain.TaskrabbitPagesFixture.*;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import com.bafrow.UnitTest;
import com.bafrow.JsonHelper;
import com.bafrow.error.domain.MissingMandatoryValueException;

@UnitTest
class RestTaskrabbitPageTest {

  @Test
  void shouldNotConvertWithoutSourcePage() {
    assertThatThrownBy(() -> RestTaskrabbitPage.from(null, source -> "test")).isExactlyInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  void shouldNotConvertWithoutMappingFunction() {
    assertThatThrownBy(() -> RestTaskrabbitPage.from(page(), null)).isExactlyInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  void shouldMapFromDomainPage() {
    RestTaskrabbitPage<String> page = RestTaskrabbitPage.from(page(), Function.identity());

    assertThat(page.getContent()).containsExactly("test");
    assertThat(page.getCurrentPage()).isEqualTo(2);
    assertThat(page.getPageSize()).isEqualTo(10);
    assertThat(page.getTotalElementsCount()).isEqualTo(21);
    assertThat(page.getPagesCount()).isEqualTo(3);
  }

  @Test
  void shouldGetPageCountForPageLimit() {
    RestTaskrabbitPage<String> page = RestTaskrabbitPage.from(pageBuilder().totalElementsCount(3).pageSize(3).build(), Function.identity());

    assertThat(page.getPagesCount()).isEqualTo(1);
  }

  @Test
  void shouldSerializeToJson() {
    assertThat(JsonHelper.writeAsString(RestTaskrabbitPage.from(page(), Function.identity()))).isEqualTo(json());
  }

  private String json() {
    return """
        {"content":["test"],\
        "currentPage":2,\
        "pageSize":10,\
        "totalElementsCount":21,\
        "pagesCount":3,\
        "hasPrevious":true,\
        "hasNext":false\
        }\
        """;
  }
}
