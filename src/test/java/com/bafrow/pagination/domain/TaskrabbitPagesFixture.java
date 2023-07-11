package com.bafrow.pagination.domain;

import java.util.List;

import com.bafrow.pagination.domain.TaskrabbitPage.TaskrabbitPageBuilder;

public final class TaskrabbitPagesFixture {

  private TaskrabbitPagesFixture() {}

  public static TaskrabbitPage<String> page() {
    return pageBuilder().build();
  }

  public static TaskrabbitPageBuilder<String> pageBuilder() {
    return TaskrabbitPage.builder(List.of("test")).currentPage(2).pageSize(10).totalElementsCount(21);
  }
}
