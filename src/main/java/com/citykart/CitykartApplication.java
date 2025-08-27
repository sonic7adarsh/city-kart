package com.citykart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("com.citykart")
@EnableScheduling
public class CitykartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CitykartApplication.class, args);
    }
}
