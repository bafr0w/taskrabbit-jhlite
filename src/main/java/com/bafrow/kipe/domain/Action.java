package com.bafrow.kipe.domain;

import com.bafrow.error.domain.Assert;

public record Action(String action) {
  public Action {
    Assert.notBlank("action", action);
  }
  
  @Override
  public String toString() {
    return action();
  }
}
