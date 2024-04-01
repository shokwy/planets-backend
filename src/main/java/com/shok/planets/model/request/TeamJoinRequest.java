package com.shok.planets.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户加入队伍请求体
 *
 * @author wy
 */
@Data
public class TeamJoinRequest implements Serializable {

    /**
     * id
     */
    private Long teamId;

    /**
     * 密码
     */
    private String password;
}