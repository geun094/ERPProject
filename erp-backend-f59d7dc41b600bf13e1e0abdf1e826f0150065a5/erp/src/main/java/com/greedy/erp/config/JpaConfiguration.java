package com.greedy.erp.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.greedy.erp"})			
@EnableJpaRepositories(basePackages = "com.greedy.erp")
public class JpaConfiguration {

}
