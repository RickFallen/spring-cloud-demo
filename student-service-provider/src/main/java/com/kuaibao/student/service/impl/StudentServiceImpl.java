package com.kuaibao.student.service.impl;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuaibao.student.dto.StudentDTO;
import com.kuaibao.student.mapper.Student;
import com.kuaibao.student.mapper.StudentMapper;
import com.kuaibao.student.service.StudentService;
import com.kuaibao.utils.Assertion;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2019-06-13
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Value("${server.port}")
    private Integer port;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public String getName() {
        return "Student-" + Thread.currentThread().getName();
    }

    @Override
    public Integer getAge() {
        return port;
    }

    @Override
    public String saveOrUpdate(StudentDTO studentDTO) {
        String id = studentDTO.getId();

        if(StringUtils.isEmpty(id)){
            Student student = new Student();
            /**
             * 根据snowflake算法生成64位ID,也可生成32位UUID
             * 如果Student实体类中的id加了@TableId注解,则可以不用自己生成id
             */
            id = IdWorker.getIdStr();

            try {
                BeanUtils.copyProperties(student,studentDTO);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            student.setId(id);

            this.save(student);
        }else {
            Student student = this.getById(id);
            Assertion.notNull(student, "无效的id");
            try {
                BeanUtils.copyProperties(student,studentDTO);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            this.updateById(student);
        }
        return id;
    }
}
