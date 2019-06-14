package com.kuaibao.student.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuaibao.student.dto.StudentDTO;
import com.kuaibao.student.dto.StudentQueryDTO;
import com.kuaibao.student.mapper.Student;
import com.kuaibao.student.mapper.StudentMapper;
import com.kuaibao.student.service.StudentService;
import com.kuaibao.utils.Assertion;
import com.kuaibao.utils.PageUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public IPage<StudentDTO> query(StudentQueryDTO queryDTO) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .lambda()
                .gt(Objects.nonNull(queryDTO.getAgeStart()), Student::getAge, queryDTO.getAgeStart())
                .lt(Objects.nonNull(queryDTO.getAgeEnd()), Student::getAge, queryDTO.getAgeEnd())
                .eq(Objects.nonNull(queryDTO.getName()), Student::getName, queryDTO.getName())
                .eq(Objects.nonNull(queryDTO.getTeacherId()), Student::getTeacherId, queryDTO.getTeacherId());

        IPage<Student> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        page = studentMapper.selectPage(page, queryWrapper);

        //实体类转DTO
        IPage<StudentDTO> convert = page.convert(PageUtils.getFuncion(StudentDTO.class));

        return convert;
    }
}