package com.wh.spring.admin.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wh.spring.admin.entity.Role;
import com.wh.spring.admin.entity.User;
import com.wh.spring.admin.entity.UserRole;
import com.wh.spring.admin.service.IRoleService;
import com.wh.spring.admin.service.IUserRoleService;
import com.wh.spring.admin.service.IUserService;
import com.wh.spring.admin.util.EncryptUtils;
import com.wh.spring.admin.util.RetUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * @author Mr.W
 * @since 2020 -08-06
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    IUserRoleService userRoleService;
    @Autowired
    IRoleService roleService;

    /**
     *  @author: WH
     *  @Date: 2020/8/18 11:43
     *  @Description: 用户列表
     */
    @RequestMapping("/users")
    public Map<String, Object> users(int page ,int size) {
        Map<String, Object> ret = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        Page<User> pages = new Page<>(page, size);
        Page<User> userPage = userService.page(pages);
        for (User user : userPage.getRecords()) {
            Map<String, Object> map = new HashMap<>();
            List<String> roleName = new ArrayList();
            map.put("name", user.getName());
            map.put("mobile", user.getMobile());
            map.put("username", user.getUsername());
            map.put("loginAt", user.getLoginAt());
            for (Role role : user.getRoles()) {
                roleName.add(role.getName());
            }
            map.put("roleName", roleName);
            list.add(map);
        }
        ret.put("size", userPage.getSize());
        ret.put("total", userPage.getTotal());
        ret.put("users", list);
        return RetUtils.retSuccess(ret);
    }

    /**
     * @author: WH
     * @Date: 2020 /8/17 11:12
     * @Description: 添加用户
     */
    @RequestMapping("/add-user")
    public Map<String, Object> addUser(String data) {
        Map<String, Object> parse = (Map<String, Object>) JSON.parse(data);
        User username = userService.getByUserName(parse.get("username").toString());
        if (username == null) {
            return RetUtils.retError("添加失败！用户名已存在。");
        }
        User user = new User();
        user.setName(parse.get("name").toString());
        user.setMobile(parse.get("mobile").toString());
        user.setUsername(parse.get("username").toString());
        user.setPassword(EncryptUtils.sha256(parse.get("password").toString()));
        user.setStatus((Integer) parse.get("status"));
        List<Integer> roles = JSON.parseArray(parse.get("roles").toString(), Integer.class);
        //设置用户权限
        for (Integer roleId : roles) {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoleService.save(userRole);
        }
        if (userService.save(user)) {
            return RetUtils.retSuccess("添加成功！");
        }
        return RetUtils.retError("添加失败！");
    }

    /**
     * @author: Mr.w
     * @Date: 2020/8/17 11:00
     * @Description: 编辑用户
     */
    @RequestMapping("edit-user")
    public Map<String, Object> editUser(String data) {
        System.out.println(data);
        Map<String, Object> parse = (Map<String, Object>) JSON.parse(data);
        Integer id = (Integer) parse.get("id");
        User user = userService.getById(id);
        Boolean saveBatch = true;
        if (null == user) {
            return RetUtils.retError("用户不存在！");
        }
        user.setMobile(parse.get("mobile").toString());
        user.setName(parse.get("name").toString());
        List<Integer> editRoles = JSON.parseArray(parse.get("roles").toString(), Integer.class);
        List<UserRole> userRoles = new ArrayList<>();
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        userRoleService.remove(wrapper);
        for (Integer roleId:editRoles) {
            UserRole userRole = new UserRole();
            userRole.setUserId(id);
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
        }
        saveBatch=userRoleService.saveBatch(userRoles);
        boolean update = userService.saveOrUpdate(user);
        System.out.println(update + "--------" + saveBatch);
        if (update && saveBatch) {
            return RetUtils.retSuccess("更新成功！");
        }
        return RetUtils.retError("更新失败！");
    }

    /**
     *  @author: WH
     *  @Date: 2020/8/18 11:48
     *  @Description: 用户信息
     */
    @RequestMapping("/info-user")
    public Map<String,Object> infoUser(Integer id){
        System.out.println(id);
        User user = userService.getById(id);
        if (user==null){
            return RetUtils.retError("用户不存在!");
        }
        Map<String, Object> ret = new HashMap<>();
        List<Role> roles = user.getRoles();
        ret.put("id",user.getId());
        ret.put("name",user.getName());
        ret.put("username",user.getUsername());
        ret.put("mobile",user.getMobile());
        ret.put("status",user.getStatus());
        List<Role> roleList = roleService.list();
        List<Map<String, Object>> arrayList = new ArrayList<>();
        for (Role r : roleList) {
            Map<String, Object> hashMap = new HashMap<>();
                    hashMap.put("name",r.getName());
                    hashMap.put("value",r.getId());
                    hashMap.put("flag",false);
                    arrayList.add(hashMap);
        }
        for (Map<String, Object> arrayList1:arrayList){
            for (Role role:roles) {
                if (arrayList1.get("name").equals(role.getName())){
                    arrayList1.put("flag",true);
                }

            }
        }

        ret.put("roles",arrayList);
        return RetUtils.retSuccess(ret);
    }

}
