package com.wh.spring.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wh.spring.admin.entity.UserRole;
import com.wh.spring.admin.mapper.UserRoleMapper;
import com.wh.spring.admin.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author Mr.W
 * @since 2020-08-06
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public UserRole isExist(Integer uesrId, Integer roleId) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uesrId);
        wrapper.eq("role_id",roleId);
        return baseMapper.selectOne(wrapper);
    }
}
