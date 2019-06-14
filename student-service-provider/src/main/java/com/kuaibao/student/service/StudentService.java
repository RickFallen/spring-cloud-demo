package com.kuaibao.student.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuaibao.student.dto.StudentDTO;
import com.kuaibao.student.dto.StudentQueryDTO;
import com.kuaibao.student.mapper.Student;

import java.util.List;

public interface StudentService extends IService<Student> {
    /**
     * 微服务测试用
     * @return
     */
    String getName();

    /**
     * 微服务测试用
     * @return
     */
    Integer getPort();

    /**
     * 添加 / 修改
     * @param studentDTO
     * @return
     */
    String saveOrUpdate(StudentDTO studentDTO);

    /**
     * lambda风格的查询
     * @param queryDTO
     * @return
     */
    IPage<StudentDTO> queryByLambda(StudentQueryDTO queryDTO);

    /**
     * 普通风格的查询
     * @param queryDTO
     * @return
     */
    IPage<StudentDTO> queryNormal(StudentQueryDTO queryDTO);

    /**
     * 原始xml风格查询
     * @param queryDTO
     * @return
     */
    IPage<StudentDTO> queryByXML(StudentQueryDTO queryDTO);
}
