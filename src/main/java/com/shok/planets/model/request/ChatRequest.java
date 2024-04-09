package com.shok.planets.model.request;

import lombok.Data;

import java.io.Serializable;
@Data
public class ChatRequest implements Serializable {

    /**
     * 队伍聊天室id
     */
    private Long teamId;
    /**
     * 发送消息id
     */
    private Long fromId;

    /**
     * 接收消息id
     */
    private Long toId;
}
