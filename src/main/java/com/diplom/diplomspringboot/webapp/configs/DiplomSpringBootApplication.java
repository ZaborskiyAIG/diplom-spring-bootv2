package com.diplom.diplomspringboot.webapp.configs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.diplom.diplomspringboot")
@EntityScan("com.diplom.diplomspringboot.models.entity")
public class DiplomSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomSpringBootApplication.class, args);
    }
}
