package com.farhutdinov.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaServeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServeApplication.class, args);
    }
}
