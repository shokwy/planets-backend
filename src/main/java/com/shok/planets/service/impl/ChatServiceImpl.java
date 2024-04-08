package com.shok.planets.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shok.planets.model.domain.Chat;
import com.shok.planets.service.ChatService;
import com.shok.planets.mapper.ChatMapper;
import org.springframework.stereotype.Service;

/**
* @author SHOK
* @description 针对表【chat(聊天消息表)】的数据库操作Service实现
* @createDate 2024-04-08 18:31:40
*/
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat>
    implements ChatService{

}




