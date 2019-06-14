package com.kuaibao.student.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuaibao.student.dto.StudentDTO;
import com.kuaibao.student.dto.StudentQueryDTO;
import com.kuaibao.student.mapper.Student;

import java.util.List;

public interface StudentService extends IService<Student> {
    String getName();

    Integer getAge();

    String saveOrUpdate(StudentDTO studentDTO);

    IPage<StudentDTO> query(StudentQueryDTO queryDTO);
}
