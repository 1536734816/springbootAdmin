package com.wh.spring.admin.Interceptor;


import com.wh.spring.admin.entity.Role;
import com.wh.spring.admin.entity.User;
import com.wh.spring.admin.mapper.RoleMapper;
import com.wh.spring.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {
    /**
     * @author : WH
     * @date : 2020-08-06 10:57
     **/
    @Autowired
    private IUserService userService;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //查数据库
        System.out.println("我在MyUserDetailsService");
        System.out.println(userName);
        User user = userService.getByUserName(userName);
        if (null != user) {
            List<Role> roles =roleMapper.getRolesByUserId(user.getId());
            user.setAuthorities(roles);
        }
        //System.out.println("我在MyUserDetailsService->"+user);
        if (user==null){
            throw new UsernameNotFoundException("找不到该账户信息！");//抛出异常，会根据配置跳到登录失败页面
        }
        return user;


    }
}
