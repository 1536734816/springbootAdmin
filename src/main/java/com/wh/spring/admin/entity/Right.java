package com.wh.spring.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wh.spring.admin.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author Mr.W
 * @since 2020-08-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_right",resultMap = "rightToRoles")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Right extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父权限ID
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

    /**
     * 请求路径
     */
    private String accessPath;

    /**
     * 是否允许通过
     */
    private Integer pass;

    @TableField(exist = false)
    ArrayList<Role> roles;


}
