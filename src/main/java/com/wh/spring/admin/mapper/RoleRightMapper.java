package com.wh.spring.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wh.spring.admin.entity.RoleRight;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 角色权限 Mapper 接口
 * </p>
 *
 * @author Mr.W
 * @since 2020-08-06
 */
@Repository
public interface RoleRightMapper extends BaseMapper<RoleRight> {

    //Right findRoleByRight(int right_id);
    //
    //Role findRole(int role_id);


}
