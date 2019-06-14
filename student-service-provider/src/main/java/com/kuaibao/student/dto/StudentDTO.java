package com.kuaibao.student.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@ApiModel
public class StudentDTO  {
    /**
     * id
     */
    @ApiModelProperty(value = "ID" , dataType = "java.lang.String")
    private String id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "考试分类ID" , required = true , dataType = "java.lang.String")
    @NotNull(message = "姓名不能为空")
    private String name;

    /**
     * 年龄 1-100
     */
    @Range(min = 1 , max = 100 , message = "年龄必须为1-100")
    @NotNull
    @ApiModelProperty(value = "年龄  必须为1-100", dataType = "java.lang.Integer")
    private Integer age;

    /**
     * 是否被开除
     */
    @ApiModelProperty(value = "是否被开除  1:被开除 0:默认", dataType = "java.lang.String")
    private String blocked;

    /**
     * 教师ID
     */
    @ApiModelProperty(value = "教师ID", dataType = "java.lang.String")
    private String teacherId;

}
