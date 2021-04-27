package com.tcode.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class TcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcodeApplication.class, args);
    }
}
