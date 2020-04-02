package com.farhutdinov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;


@SpringBootApplication
@RefreshScope
//@EnableJpaRepositories(basePackages = "com.farhutdinov.licenses.repository")
//@EntityScan(basePackages = "com.farhutdinov.licenses.model")

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
