package student;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuaibao.StudentServiceProviderApplication;
import com.kuaibao.student.dto.StudentDTO;
import com.kuaibao.student.dto.StudentQueryDTO;
import com.kuaibao.student.mapper.Student;
import com.kuaibao.student.mapper.StudentMapper;
import com.kuaibao.student.service.StudentService;
import com.kuaibao.utils.QueryDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * mybatis - plus 用法详见文档
 *
 * https://mp.baomidou.com/guide/crud-interface.html
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentServiceProviderApplication.class)
public class StudentTest {
    @Resource
    StudentService studentService;
    @Resource
    StudentMapper studentMapper;

    @Test
    public void addStu(){
        Student student = new Student();
        student.setAge(15);
        student.setName(Thread.currentThread().getName());
        /**
         * Student 类中如果id上有@TableId注解 则无需自动设置id
         */
        //student.setId(IdWorker.get32UUID());
        studentMapper.insert(student);
    }

    /**
     * 更新 , 默认空值不更新
     */
    @Test
    public void update(){
        Student student = new Student();
        student.setId("1139097098319650818");
        student.setTeacherId(IdWorker.getIdStr());
        studentMapper.updateById(student);
    }

    /**
     * 空值更新
     */
    @Test
    public void updateByNullValue(){
        UpdateWrapper<Student> studentWrapper = new UpdateWrapper<>();
        studentWrapper.set("teacher_id", null);
        studentWrapper.set("name", null);
        studentWrapper.eq("id", "1139097098319650818");
        int update = studentMapper.update(new Student(), studentWrapper);
        System.out.println(update);
    }



    @Test
    public void query() throws JsonProcessingException {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age",12);
        //只查询id和name字段
        queryWrapper.select("id", "name");
        List<Student> students = studentMapper.selectList(queryWrapper);
        System.out.println(new ObjectMapper().writeValueAsString(students));
    }


    /**
     * lambda方式查询
     * 不用手动填写字段,方便不易出错
     */
    @Test
    public void queryWithLambda() throws JsonProcessingException {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .lambda()
                .eq(Student::getAge, 12)
                .eq(Student::getTeacherId,"123");
        List<Student> students = studentMapper.selectList(queryWrapper);
        System.out.println(new ObjectMapper().writeValueAsString(students));
    }

    /**
     * 分页
     */
    @Test
    public void page() throws JsonProcessingException {
        int pageNum = 1;
        int pageSize = 2;
        IPage<Student> page = new Page<>(pageNum,pageSize);
        page = studentService.page(page);
        System.out.println(new ObjectMapper().writeValueAsString(page));
    }

    /**
     * lambda方式查询  分页
     */
    @Test
    public void queryWithLambdaAndPage() throws JsonProcessingException {
        int pageNum = 1;
        int pageSize = 2;
        IPage<Student> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .lambda()
                .eq(Student::getAge, 12)
                .eq(Student::getTeacherId,"123");
        page = studentMapper.selectPage(page, queryWrapper);
        System.out.println(new ObjectMapper().writeValueAsString(page));
    }

    @Test
    public void queryTest(){
        IPage<StudentDTO> query = studentService.query(new StudentQueryDTO());
        System.out.println(JSON.toJSONString(query));
    }
}
