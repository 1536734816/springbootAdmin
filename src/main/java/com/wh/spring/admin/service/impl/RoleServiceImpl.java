package com.wh.spring.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wh.spring.admin.entity.Role;
import com.wh.spring.admin.mapper.RoleMapper;
import com.wh.spring.admin.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author Mr.W
 * @since 2020-08-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<Role> getRolesByUserId(Integer id) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return null;
    }
}
