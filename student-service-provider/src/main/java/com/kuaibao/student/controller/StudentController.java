package com.kuaibao.student.controller;

import com.kuaibao.student.dto.StudentDTO;
import com.kuaibao.student.mapper.Student;
import com.kuaibao.student.service.StudentService;
import com.kuaibao.utils.Assertion;
import com.kuaibao.utils.Response;
import com.thoughtworks.xstream.core.BaseException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class StudentController {
    @Resource
    private StudentService studentService;

    @RequestMapping(value = "/stu/getName" ,method = RequestMethod.GET)
    public String getName(){
        return studentService.getName();
    }

    @RequestMapping(value = "/stu/getAge" ,method = RequestMethod.GET)
    public Integer getAge(){
        return studentService.getAge();
    }

    @RequestMapping(value = "/stu/save" ,method = RequestMethod.POST)
    public Response save(@RequestBody @Valid StudentDTO studentDTO , BindingResult result){
        Assertion.throwError(result);
        String studentId = studentService.saveOrUpdate(studentDTO);
        return Response.of().success().body(studentId);
    }

    @RequestMapping(value = "/stu/{id}" ,method = RequestMethod.POST)
    public Response save(@PathVariable String id){
        Student student = studentService.getById(id);
        Assertion.notNull(student, "无效的id");
        return Response.of().success().body(student);
    }
}
