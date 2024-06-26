package com.shok.planets.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 性别 0-男   1-女
     */
    private Integer gender;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 0 - 正
     * 常
     */
    private Integer userStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除（逻
     * 辑删除）
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 用户权限 0
     * - 普通用户 1 - 管理员
     */
    private Integer userRole;

    /**
     * 个人描述
     */
    private String introduction;

    /**
     * 标签列表
     */
    private String tags;

    /**
     * 添加的好友
     */
    private String userIds;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}