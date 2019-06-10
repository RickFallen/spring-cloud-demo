package com.kuaibao.student;

import com.kuaibao.student.config.StudentProviderConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RibbonClient(name = "student-service-provider", configuration = StudentProviderConfiguration.class)
public class StudentServiceConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentServiceConsumerApplication.class, args);
    }
}
