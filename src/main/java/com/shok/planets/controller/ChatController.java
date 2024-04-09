package com.shok.planets.controller;



import com.shok.planets.common.BaseResponse;
import com.shok.planets.common.ErrorCode;
import com.shok.planets.common.ResultUtils;
import com.shok.planets.constant.ChatConstant;
import com.shok.planets.exception.BusinessException;
import com.shok.planets.model.domain.User;
import com.shok.planets.model.request.ChatRequest;
import com.shok.planets.model.vo.MessageVo;
import com.shok.planets.service.ChatService;
import com.shok.planets.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/chat")
@Slf4j
public class ChatController {
    @Resource
    private ChatService chatService;
    @Resource
    private UserService userService;

    @PostMapping("/privateChat")
    public BaseResponse<List<MessageVo>> getPrivateChat(@RequestBody ChatRequest chatRequest, HttpServletRequest request) {
        if (chatRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求有误");
        }

        User loginUser = userService.getLoginUser(request);
        log.info("私聊：{},{}",chatRequest,loginUser);
        List<MessageVo> privateChat = chatService.getPrivateChat(chatRequest, ChatConstant.PRIVATE_CHAT, loginUser);
        return ResultUtils.success(privateChat);
    }
    @GetMapping("/hallChat")
    public BaseResponse<List<MessageVo>> getHallChat(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        List<MessageVo> hallChat = chatService.getHallChat(ChatConstant.HALL_CHAT, loginUser);
        return ResultUtils.success(hallChat);
    }

    @PostMapping("/teamChat")
    public BaseResponse<List<MessageVo>> getTeamChat(@RequestBody ChatRequest chatRequest, HttpServletRequest request) {
        if (chatRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求有误");
        }
        User loginUser = userService.getLoginUser(request);
        log.info("群聊：{},{}",chatRequest,loginUser);
        List<MessageVo> teamChat = chatService.getTeamChat(chatRequest, ChatConstant.TEAM_CHAT, loginUser);
        return ResultUtils.success(teamChat);
    }
}
