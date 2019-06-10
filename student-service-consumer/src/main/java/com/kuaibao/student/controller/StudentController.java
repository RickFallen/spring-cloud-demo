package com.kuaibao.student.controller;

import com.kuaibao.student.consumer.StudentServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    StudentServcie studentServcie;

    @Value("${server.port}")
    private Integer port;

    @RequestMapping(value = "/student" , method = RequestMethod.GET)
    public String getStudent(){
        return "student name is :" + studentServcie.getName() + ",age is :" + studentServcie.getAge() + "\n" +
                "from consumer port :" + port;
    }

    @RequestMapping(value = "/test" , method = RequestMethod.GET)
    public String test(){
        return "0";
    }
}
