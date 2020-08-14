package com.wh.spring.admin.config;

import com.wh.spring.admin.Interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userService;
    @Autowired
    UrlAuthenticationFailureHandler urlAuthenticationFailureHandler;
    @Autowired
    UrlAuthenticationSuccessHandler urlAuthenticationSuccessHandler;
    @Autowired
    MyLoginUrlAuthenticationEntryPoint myLoginUrlAuthenticationEntryPoint;
    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    UrlLogoutSuccessHandler urlLogoutSuccessHandler;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        ////校验用户
        auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
            //对密码进行加密
            @Override
            public String encode(CharSequence charSequence) {
                System.out.println(charSequence.toString());
                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
            }

            //对密码进行判断匹配
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                System.out.println(s);

                String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                System.out.println(encode);
                boolean res = s.equals(encode);
                return res;
            }
        });


    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //不拦截路由
                .antMatchers("/admin/login", "/admin/logout").permitAll()
                //所有请求都要过认证
                .anyRequest().authenticated()
                //登陆路由
                .and().formLogin().loginProcessingUrl("/admin/login")
                //默认的用户名参数
                .usernameParameter("username")
                //默认的密码参数
                .passwordParameter("password")
                .successHandler(urlAuthenticationSuccessHandler)
                .failureHandler(urlAuthenticationFailureHandler).permitAll()
                //关闭拦截未登录时自动跳转，改为返回json信息
                .and().exceptionHandling().accessDeniedHandler(myAccessDeniedHandler).authenticationEntryPoint(myLoginUrlAuthenticationEntryPoint)
                .and().logout().logoutUrl("/admin/logout")
                //注销成功处理器
                .logoutSuccessHandler(urlLogoutSuccessHandler).permitAll()
                .and()
                .cors() //跨域
                .and()
                //关闭csrf防护，类似于防火墙，不关闭上面的设置不会真正生效。
                .csrf().disable()
                .sessionManagement()
                .maximumSessions(1);

    }


}