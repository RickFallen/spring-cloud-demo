package com.kuaibao.student.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{
    @Value("${server.port}")
    private Integer port;

    @Override
    public String getName() {
        return "Student-" + Thread.currentThread().getName();
    }

    @Override
    public Integer getAge() {
        return port;
    }
}
