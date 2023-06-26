package com.idle.osmas.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.idle.osmas")
public class OsmasApplication {

    public static void main(String[] args) {
        SpringApplication.run(OsmasApplication.class, args);
    }
}
