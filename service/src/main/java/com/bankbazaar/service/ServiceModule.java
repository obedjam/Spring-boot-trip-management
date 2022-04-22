package com.bankbazaar.service;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bankbazaar.*"})
@EnableJpaRepositories(basePackages = {"com.bankbazaar.core.*"})
@EntityScan(basePackages = {"com.bankbazaar.core.*"})
public class ServiceModule {
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
    public static void main(String[] args) {
    SpringApplication.run(ServiceModule.class, args);
}
}
