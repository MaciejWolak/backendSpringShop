package com.example.webshopapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class WebshopappApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebshopappApplication.class, args);
    }

}
