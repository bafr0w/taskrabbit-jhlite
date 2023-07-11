package com.bafrow.error_generator.domain;

import com.bafrow.error.domain.NullElementInCollectionException;

public final class NullElementInCollectionExceptionFactory {

  private NullElementInCollectionExceptionFactory() {}

  public static NullElementInCollectionException nullElementInCollection() {
    return new NullElementInCollectionException("field");
  }
}
