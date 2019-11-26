package com.example.safe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
public class SafeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SafeApplication.class, args);
    }

}
