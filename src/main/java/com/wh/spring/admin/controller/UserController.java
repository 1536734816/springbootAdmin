package com.wh.spring.admin.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author Mr.W
 * @since 2020-08-06
 */
@RestController
@RequestMapping("/admin/")
public class UserController {

    @RequestMapping("/cs")
    public String cs(){
        return "你好";
    }

    @RequestMapping("/login")
    public String login(){
        return "长沙市";
    }
}
