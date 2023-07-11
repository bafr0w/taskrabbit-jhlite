package com.bafrow.pagination.infrastructure.secondary;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.bafrow.error.domain.Assert;
import com.bafrow.pagination.domain.TaskrabbitPage;
import com.bafrow.pagination.domain.TaskrabbitPageable;

public final class TaskrabbitPages {

  private TaskrabbitPages() {}

  public static Pageable from(TaskrabbitPageable pagination) {
    return from(pagination, Sort.unsorted());
  }

  public static Pageable from(TaskrabbitPageable pagination, Sort sort) {
    Assert.notNull("pagination", pagination);
    Assert.notNull("sort", sort);

    return PageRequest.of(pagination.page(), pagination.pageSize(), sort);
  }

  public static <S, T> TaskrabbitPage<T> from(Page<S> springPage, Function<S, T> mapper) {
    Assert.notNull("springPage", springPage);
    Assert.notNull("mapper", mapper);

    return TaskrabbitPage
      .builder(springPage.getContent().parallelStream().map(mapper).toList())
      .currentPage(springPage.getNumber())
      .pageSize(springPage.getSize())
      .totalElementsCount(springPage.getTotalElements())
      .build();
  }
}
