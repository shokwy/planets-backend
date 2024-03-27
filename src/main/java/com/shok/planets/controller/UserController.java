package com.shok.planets.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shok.planets.common.BaseResponse;
import com.shok.planets.common.ErrorCode;
import com.shok.planets.common.ResultUtils;
import com.shok.planets.constant.UserConstant;
import com.shok.planets.exception.BusinessException;
import com.shok.planets.model.domain.User;
import com.shok.planets.model.request.UserLoginRequest;
import com.shok.planets.model.request.UserRegisterRequest;
import com.shok.planets.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户接口
 * @author wy
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户接口")
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param userRegisterRequest 注册参数
     * @return 用户id
     */
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){

        log.info("用户注册：{}",userRegisterRequest);
        if (userRegisterRequest == null){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();


        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     * @param userLoginRequest 登录参数
     * @param request
     * @return 用户信息
     */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){

        log.info("用户登录：{}",userLoginRequest);
        if (userLoginRequest == null){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("用户注销")
    public BaseResponse<Integer> userLogout(HttpServletRequest request){
        log.info("用户注销");
        if (request == null){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        Integer result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前用户信息
     * @param request
     * @return
     */
    @GetMapping("/current")
    @ApiOperation("获取当前用户信息")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        log.info("获取当前用户信息");
        if (request == null){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null){
            return null;
        }
        Long userId = currentUser.getId();
        // todo 校验用户是否合法
        User user = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    /**
     * 用户查询
     * @param username 用户名
     * @param request
     * @return 用户列表
     */
    @GetMapping("/search")
    @ApiOperation("用户查询")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request){
        log.info("用户查询:{}",username);
        if (!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> list = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(list);
    }

    /**
     * 用户删除
     * @param id
     * @param request
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation("用户删除")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request){
        log.info("用户删除:{}",id);
        if (!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (id <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = userService.removeById(id);
        return ResultUtils.success(result);
    }



    //根据标签查询用户
    @GetMapping("/search/tags")
    @ApiOperation("根据标签查询用户")
    public BaseResponse<List<User>> searchUsersByTags(@RequestParam(required = false) List<String> tagNameList){
        log.info("根据标签查询用户:{}",tagNameList);
        if (CollectionUtils.isEmpty(tagNameList)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        List<User> userList = userService.searchUsersByTags(tagNameList);
        return ResultUtils.success(userList);
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("用户更新")
    public BaseResponse<Integer> updateUser(@RequestBody User user, HttpServletRequest request){
        log.info("用户更新:{}",user);
        //1.校验用户是否为空
        if (user == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //2.校验权限
        User loginUser = userService.getLoginUser(request);

        //3.更新
        int result = userService.updateUser(user, loginUser);
        return ResultUtils.success(result);
    }


}

