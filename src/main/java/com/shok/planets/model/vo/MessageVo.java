package com.shok.planets.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageVo implements Serializable {

    private WebSocketVo formUser;
    private WebSocketVo toUser;
    private Long teamId;
    private String text;


    //12day
    private Boolean isMy = false;
    private Integer chatType;


    private Boolean isAdmin = false;
    private String createTime;
}
