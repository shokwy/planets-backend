package com.shok.planets.constant;

public interface ChatConstant {
    /**
     * 私聊
     */
    int PRIVATE_CHAT = 1;
    /**
     * 队伍群聊
     */
    int TEAM_CHAT = 2;
    /**
     * 大厅聊天
     */
    int HALL_CHAT = 3;

    String CACHE_CHAT_HALL = "planets:chat:chat_records:chat_hall";

    String CACHE_CHAT_PRIVATE = "planets:chat:chat_records:chat_private";

    String CACHE_CHAT_TEAM = "planets:chat:chat_records:chat_team";
}
