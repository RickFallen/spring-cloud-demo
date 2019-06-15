package com.kuaibao.student.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuaibao.student.dto.StudentDTO;
import com.kuaibao.student.dto.StudentQueryDTO;
import com.kuaibao.utils.KbPage;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-06-13
 */
public interface StudentMapper extends BaseMapper<Student> {
    /**
     * <p>
     * 查询 : 根据queryDTO查询列表，分页显示
     * 注意!!: 如果入参是有多个,需要加注解指定参数名才能在xml中取值
     * </p>
     *
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @param queryDTO 参数
     * @return 分页对象
     */
    KbPage<StudentDTO> queryList(KbPage<StudentDTO> page, @Param("param") StudentQueryDTO queryDTO);
}
