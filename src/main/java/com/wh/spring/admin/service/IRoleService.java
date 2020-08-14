package com.wh.spring.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wh.spring.admin.entity.Role;


import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author Mr.W
 * @since 2020-08-06
 */
public interface IRoleService extends IService<Role> {
    public List<Role> getRolesByUserId(Integer id);

}
