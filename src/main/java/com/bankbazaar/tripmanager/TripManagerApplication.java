package com.bankbazaar.tripmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TripManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripManagerApplication.class, args);
    }

}