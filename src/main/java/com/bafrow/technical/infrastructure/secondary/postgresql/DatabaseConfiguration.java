package com.bafrow.technical.infrastructure.secondary.postgresql;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages =  { "com.bafrow" }, enableDefaultTransactions = false)
public class DatabaseConfiguration {}
