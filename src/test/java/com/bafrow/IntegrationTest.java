package com.bafrow;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.springframework.security.test.context.support.WithMockUser;
import com.bafrow.authentication.infrastructure.primary.TestSecurityConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;

@WithMockUser
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DisplayNameGeneration(ReplaceCamelCase.class)
@SpringBootTest(classes = { TaskrabbitApp.class, TestSecurityConfiguration.class })
public @interface IntegrationTest {
  @AliasFor(annotation = SpringBootTest.class)
  public String[] properties() default {};
}
