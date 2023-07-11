package com.bafrow.kipe.domain;

import static org.assertj.core.api.Assertions.*;

import com.bafrow.UnitTest;
import org.junit.jupiter.api.Test;

@UnitTest
class ActionTest {

  @Test
  void shouldGetActionAsToString() {
    assertThat(new Action("act")).hasToString("act");
  }
}
