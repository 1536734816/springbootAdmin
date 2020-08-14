package com.wh.spring.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wh.spring.admin.entity.User;


/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author Mr.W
 * @since 2020-08-06
 */
public interface IUserService extends IService<User> {
    public User getByUserName(String username);

}
