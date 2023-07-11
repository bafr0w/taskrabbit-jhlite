package com.bafrow.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import com.bafrow.ComponentTest;

@ComponentTest
@RunWith(Cucumber.class)
@CucumberOptions(
  glue = "com.bafrow",
  plugin = {
    "pretty", "json:target/cucumber/cucumber.json", "html:target/cucumber/cucumber.htm", "junit:target/cucumber/TEST-cucumber.xml",
  },
  features = "src/test/features"
)
public class CucumberTest {}
