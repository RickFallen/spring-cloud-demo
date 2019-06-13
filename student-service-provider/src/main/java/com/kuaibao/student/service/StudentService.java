package com.kuaibao.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kuaibao.student.dto.StudentDTO;
import com.kuaibao.student.mapper.Student;

public interface StudentService extends IService<Student> {
    String getName();

    Integer getAge();

    String saveOrUpdate(StudentDTO studentDTO);
}
