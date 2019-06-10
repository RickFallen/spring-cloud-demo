package com.kuaibao.student.controller;

import com.kuaibao.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/stu/getName" ,method = RequestMethod.GET)
    public String getName(){
        return studentService.getName();
    }

    @RequestMapping(value = "/stu/getAge" ,method = RequestMethod.GET)
    public Integer getAge(){
        return studentService.getAge();
    }
}
