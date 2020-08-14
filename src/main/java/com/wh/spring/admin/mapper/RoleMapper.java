package com.wh.spring.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wh.spring.admin.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author Mr.W
 * @since 2020-08-06
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    @Select( "SELECT A.id,A.name FROM sys_role A LEFT JOIN sys_user_role B ON A.id=B.role_id WHERE B.user_id=${userId}" )
    List<Role> getRolesByUserId(@Param("userId") Integer userId);

}
