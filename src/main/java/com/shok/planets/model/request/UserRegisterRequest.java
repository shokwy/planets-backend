package com.shok.planets.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求
 * @author wy
 * @version 1.0
 */
@Data
public class UserRegisterRequest implements Serializable{

    private static final long serialVersionUID = 7125469034316914278L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

}
