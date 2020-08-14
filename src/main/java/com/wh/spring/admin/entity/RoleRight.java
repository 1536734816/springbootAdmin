package com.wh.spring.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wh.spring.admin.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色权限
 * </p>
 *
 * @author Mr.W
 * @since 2020-08-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role_right")
public class RoleRight extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 权限ID
     */
    private Integer rightId;

    /**
     *权限
     */
    @TableField(exist = false)
    private Right right;

    /**
     *角色
     */
    @TableField(exist = false)
    private Role role;




}
