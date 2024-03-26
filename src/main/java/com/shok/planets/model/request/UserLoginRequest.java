package com.shok.planets.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求
 * @author wy
 * @version 1.0
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -5211384516432626343L;

    private String userAccount;

    private String userPassword;
}
