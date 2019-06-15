package com.kuaibao.student.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kuaibao.student.dto.StudentDTO;
import com.kuaibao.student.dto.StudentQueryDTO;
import com.kuaibao.student.mapper.Student;
import com.kuaibao.student.service.StudentService;
import com.kuaibao.utils.Assertion;
import com.kuaibao.utils.FunctionUtils;
import com.kuaibao.utils.KbPage;
import com.kuaibao.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "学生", tags = "学生")
public class StudentController {
    @Resource
    private StudentService studentService;

    @ApiOperation("获取线程名称")
    @RequestMapping(value = "/threadName" ,method = RequestMethod.GET)
    public String getName(){
        return studentService.getName();
    }

    @ApiOperation("获取端口号")
    @RequestMapping(value = "/port" ,method = RequestMethod.GET)
    public Integer getAge(){
        return studentService.getPort();
    }

    @ApiOperation("保存学生信息")
    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    public Response save(@RequestBody @Valid StudentDTO studentDTO , BindingResult result){
        Assertion.checkError(result);
        String studentId = studentService.saveOrUpdate(studentDTO);
        return Response.of().success().body(studentId);
    }

    @ApiOperation("获取学生信息")
    @RequestMapping(value = "/get" ,method = RequestMethod.GET)
    public Response<StudentDTO> save(@RequestParam String id){
        Student student = studentService.getById(id);
        Assertion.notNull(student, "无效的id");
        StudentDTO studentDTO = new StudentDTO();
        try {
            BeanUtils.copyProperties(studentDTO,student);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return Response.of().success().body(studentDTO);
    }

    @ApiOperation("学生查询(lambda风格)")
    @RequestMapping(value = "/queryByLambda" ,method = RequestMethod.POST)
    public Response<KbPage<StudentDTO>> queryByLambda(@RequestBody StudentQueryDTO queryDTO){
        KbPage<StudentDTO> list = studentService.queryByLambda(queryDTO);
        return Response.of().success().body(list);
    }

    @ApiOperation("学生查询(普通风格)")
    @RequestMapping(value = "/queryNormal" ,method = RequestMethod.POST)
    public Response<KbPage<StudentDTO>> queryNormal(@RequestBody StudentQueryDTO queryDTO){
        KbPage<StudentDTO> list = studentService.queryNormal(queryDTO);
        return Response.of().success().body(list);
    }

    @ApiOperation("学生查询(XML风格)")
    @RequestMapping(value = "/queryByXML" ,method = RequestMethod.POST)
    public Response<KbPage<StudentDTO>> queryByXML(@RequestBody StudentQueryDTO queryDTO){
        KbPage<StudentDTO> list = studentService.queryByXML(queryDTO);
        return Response.of().success().body(list);
    }

    @ApiOperation("批量保存")
    @RequestMapping(value = "/batch" ,method = RequestMethod.POST)
    public Response<Student> queryByXML(@RequestBody List<StudentDTO> students){
        //dto转po
        List<Student> entityList = students
                                        .stream()
                                        .map(FunctionUtils.getConvertFunction(Student.class))
                                        .collect(Collectors.toList());
        boolean success = studentService.saveBatch(entityList);
        return Response.of().success().body(success);
    }
}
