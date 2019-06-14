package com.kuaibao.student.dto;

import com.kuaibao.utils.QueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class StudentQueryDTO extends QueryDTO {
    @ApiModelProperty(value = "教师ID" , dataType = "java.lang.String")
    private String teacherId;

    @ApiModelProperty(value = "考试分类ID" , required = true , dataType = "java.lang.String")
    private String name;

    @ApiModelProperty(value = "起始年龄" , required = true , dataType = "java.lang.Integer")
    private Integer ageStart;

    @ApiModelProperty(value = "结束年龄" , required = true , dataType = "java.lang.Integer")
    private Integer ageEnd;

    @ApiModelProperty(value = "入学时间开始" , required = true , dataType = "java.lang.String")
    private String joinTimeStart;

    @ApiModelProperty(value = "入学时间结束" , required = true , dataType = "java.lang.String")
    private String joinTimeEnd;
}
