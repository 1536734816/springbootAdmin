package com.wh.spring.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wh.spring.admin.entity.Right;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author Mr.W
 * @since 2020-08-06
 */
@Repository
public interface RightMapper extends BaseMapper<Right> {

    ArrayList<Right> RightToRole(Integer id);

}
