package com.bankbazaar.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bankbazaar.core.*"})
@EnableJpaRepositories(basePackages = {"com.bankbazaar.core.*"})
@EntityScan(basePackages = {"com.bankbazaar.core.*"})
public class ServiceModule {public static void main(String[] args) {
    SpringApplication.run(ServiceModule.class, args);
}

}
