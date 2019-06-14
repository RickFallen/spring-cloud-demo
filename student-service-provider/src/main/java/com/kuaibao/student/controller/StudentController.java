package com.kuaibao.student.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kuaibao.student.dto.StudentDTO;
import com.kuaibao.student.dto.StudentQueryDTO;
import com.kuaibao.student.mapper.Student;
import com.kuaibao.student.service.StudentService;
import com.kuaibao.utils.Assertion;
import com.kuaibao.utils.Response;
import com.thoughtworks.xstream.core.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "学生", tags = "学生")
public class StudentController {
    @Resource
    private StudentService studentService;

    @ApiOperation("获取名称")
    @RequestMapping(value = "/stu/getName" ,method = RequestMethod.GET)
    public String getName(){
        return studentService.getName();
    }

    @ApiOperation("获取年龄(端口号)")
    @RequestMapping(value = "/stu/getAge" ,method = RequestMethod.GET)
    public Integer getAge(){
        return studentService.getAge();
    }

    @ApiOperation("保存学生信息")
    @RequestMapping(value = "/stu/save" ,method = RequestMethod.POST)
    public Response save(@RequestBody @Valid StudentDTO studentDTO , BindingResult result){
        Assertion.throwError(result);
        String studentId = studentService.saveOrUpdate(studentDTO);
        return Response.of().success().body(studentId);
    }

    @ApiOperation("获取学生信息")
    @RequestMapping(value = "/stu/{id}" ,method = RequestMethod.POST)
    public Response save(@PathVariable String id){
        Student student = studentService.getById(id);
        Assertion.notNull(student, "无效的id");
        return Response.of().success().body(student);
    }

    @ApiOperation("学生查询")
    @RequestMapping(value = "/stu/query" ,method = RequestMethod.POST)
    public Response<List<StudentDTO>> query(@RequestBody StudentQueryDTO queryDTO){
        IPage<StudentDTO> list = studentService.query(queryDTO);
        return Response.of().success().body(list);
    }
}
