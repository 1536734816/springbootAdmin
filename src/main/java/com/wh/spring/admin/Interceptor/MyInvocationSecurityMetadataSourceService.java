package com.wh.spring.admin.Interceptor;

import com.wh.spring.admin.entity.Right;
import com.wh.spring.admin.entity.Role;
import com.wh.spring.admin.service.IRightService;
import com.wh.spring.admin.service.IRoleRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 储存请求与权限的对应关系
 *
 * @author : WH
 * @date : 2020-08-07 17:11
 **/
@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    @Autowired
    IRightService rightService;
    @Autowired
    IRoleRightService roleRightService;

    /**
     * 每一个资源所需要的角色 Collection<ConfigAttribute>决策器会用到
     */
    private static HashMap<String, Collection<ConfigAttribute>> map = null;

    /**
     * 返回请求的资源需要的角色
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        //当权限和角色为空的时候加载
        if (null == map) {
            loadResourceDefine();
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
            String url = it.next();
            if (new AntPathRequestMatcher(url).matches(request)) {
                return map.get(url);
            }
        }
        ArrayList<ConfigAttribute> attributes = new ArrayList<>();
        ConfigAttribute config = new SecurityConfig("123");
        attributes.add(config);
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 初始化 所有url 对应的角色
     */
    public void loadResourceDefine() {
        map = new HashMap<>(16);
        //权限资源 和 角色对应的表  也就是 角色权限 中间表
        //System.out.println("我在加载权限角色");
        List<Right> rights = rightService.list();
        //System.out.println("rights->"+rights);
        //某个资源url 可以被哪些角色访问
        for (Right right : rights) {
            List<ConfigAttribute> list = new ArrayList<>();
            /*此处需要做一个懒加载获取对应的数据*/
            String url = right.getAccessPath();
            //System.out.println("url->"+url);
            for (Role role : right.getRoles()) {
                ConfigAttribute roleName = new SecurityConfig(role.getName());
                list.add(roleName);
            }
            //map=>{url=[Role,Role....]}
            map.put(url, list);
        }
    }
}
