package com.shok.planets.controller;


import com.shok.planets.common.BaseResponse;
import com.shok.planets.common.ErrorCode;
import com.shok.planets.common.ResultUtils;
import com.shok.planets.exception.BusinessException;
import com.shok.planets.model.domain.User;
import com.shok.planets.model.request.FriendAddRequest;
import com.shok.planets.model.vo.FriendsRecordVO;
import com.shok.planets.service.FriendsService;
import com.shok.planets.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/friends")
@Slf4j
@Api(tags = "好友接口")
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class FriendsController {
    @Resource
    private FriendsService friendsService;

    @Resource
    private UserService userService;

    @PostMapping("/add")
    @ApiOperation("添加好友")
    public BaseResponse<Boolean> addFriendRecords(@RequestBody FriendAddRequest friendAddRequest, HttpServletRequest request) {
        if (friendAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求有误");
        }
        log.info("添加好友：{}", friendAddRequest);
        User loginUser = userService.getLoginUser(request);
        boolean addStatus = friendsService.addFriendRecords(loginUser, friendAddRequest);
        return ResultUtils.success(addStatus);
    }

    @GetMapping("getRecords")
    @ApiOperation("获取好友请求")
    public BaseResponse<List<FriendsRecordVO>> getRecords(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        List<FriendsRecordVO> friendsList = friendsService.obtainFriendApplicationRecords(loginUser);
        return ResultUtils.success(friendsList);
    }

    /**
     * 获取未读记录
     * @param request
     * @return
     */
    @GetMapping("getRecordCount")
    @ApiOperation("获取未读记录")
    public BaseResponse<Integer> getRecordCount(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        int recordCount = friendsService.getRecordCount(loginUser);
        return ResultUtils.success(recordCount);
    }

    @GetMapping("getMyRecords")
    @ApiOperation("获取我发起好友记录")
    public BaseResponse<List<FriendsRecordVO>> getMyRecords(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        List<FriendsRecordVO> myFriendsList = friendsService.getMyRecords(loginUser);
        return ResultUtils.success(myFriendsList);
    }

    @PostMapping("agree/{fromId}")
    @ApiOperation("同意好友请求")
    public BaseResponse<Boolean> agreeToApply(@PathVariable("fromId") Long fromId, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        boolean agreeToApplyStatus = friendsService.agreeToApply(loginUser, fromId);
        return ResultUtils.success(agreeToApplyStatus);
    }

    @PostMapping("canceledApply/{id}")
    @ApiOperation("撤销好友请求")
    public BaseResponse<Boolean> canceledApply(@PathVariable("id") Long id, HttpServletRequest request) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求有误");
        }
        User loginUser = userService.getLoginUser(request);
        boolean canceledApplyStatus = friendsService.canceledApply(id, loginUser);
        return ResultUtils.success(canceledApplyStatus);
    }

    @GetMapping("/read")
    @ApiOperation("已读记录")
    public BaseResponse<Boolean> toRead(@RequestParam(required = false) Set<Long> ids, HttpServletRequest request) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResultUtils.success(false);
        }
        User loginUser = userService.getLoginUser(request);
        boolean isRead = friendsService.toRead(loginUser, ids);
        return ResultUtils.success(isRead);
    }
}
