package com.wh.spring.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wh.spring.admin.entity.Role;
import com.wh.spring.admin.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author Mr.W
 * @since 2020-08-06
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
 ArrayList<Role> userToRole(int id);
    @Select( "select id , username , password from sys_user where username = #{username}" )
    User loadUserByUsername(@Param("username") String username);
}
