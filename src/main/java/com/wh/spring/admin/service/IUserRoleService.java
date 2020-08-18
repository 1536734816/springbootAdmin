package com.wh.spring.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wh.spring.admin.entity.UserRole;


/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author Mr.W
 * @since 2020-08-06
 */
public interface IUserRoleService extends IService<UserRole> {
    public UserRole isExist(Integer uesrId,Integer roleId);

}
