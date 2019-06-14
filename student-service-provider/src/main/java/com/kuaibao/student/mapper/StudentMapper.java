package com.kuaibao.student.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuaibao.student.dto.StudentDTO;
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
    IPage<StudentDTO> selectPageVo(Page page, @Param("state") Integer state);
}
