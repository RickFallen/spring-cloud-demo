package com.kuaibao.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication(scanBasePackages = "com.kuaibao.*")
@EnableEurekaClient
public class StudentServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentServiceProviderApplication.class, args);
    }
}
