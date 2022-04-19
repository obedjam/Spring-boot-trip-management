package com.bankbazaar.tripmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CoreModule {public static void main(String[] args) {
    SpringApplication.run(CoreModule.class, args);
}

}
