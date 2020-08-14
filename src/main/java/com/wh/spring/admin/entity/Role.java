package com.wh.spring.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wh.spring.admin.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

/**
 * 角色
 *
 * @author Mr.W
 * @since 2020-08-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class Role extends BaseEntity implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    /**
     * 父角色ID
     */
    private Integer parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 标签
     */
    private String tag;

    /**
     * 描述
     */
    private String description;


    @Override
    public String getAuthority() {
        return name;
    }
}
