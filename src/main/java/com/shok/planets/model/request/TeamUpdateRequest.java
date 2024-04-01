package com.shok.planets.model.request;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;

/**
 * 用户登录请求体
 *
 * @author wy
 */
@Data
public class TeamUpdateRequest implements Serializable {


    /**
     * id
     */
    private Long id;

    /**
     * 队伍名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;

    /**
     * 密码
     */
    private String password;
}