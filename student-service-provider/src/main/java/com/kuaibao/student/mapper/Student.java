package com.kuaibao.student.mapper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author
 * @since 2019-06-13
 */
@Data
public class Student extends Model<Student> {

    private static final long serialVersionUID = 1L;

    //指定ID生成方式
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄 1-100
     */
    private Integer age;

    /**
     * 是否被开除
     */
    private String blocked;

    /**
     * 教师ID
     */
    private String teacherId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
