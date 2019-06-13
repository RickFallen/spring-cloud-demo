package com.kuaibao.student.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class StudentDTO  {
    /**
     * id
     */
    private String id;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    private String name;

    /**
     * 年龄 1-100
     */
    @Range(min = 1 , max = 100 , message = "年龄必须为1-100")
    @NotNull
    private Integer age;

    /**
     * 是否被开除
     */
    private String blocked;

    /**
     * 教师ID
     */
    private String teacherId;

}
