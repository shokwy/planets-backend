package com.shok.planets.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;
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

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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

        String username = userRegisterRequest.getUsername();
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();

        if (StringUtils.isAnyBlank(username,userAccount,userPassword,checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long result = userService.userRegister(userRegisterRequest);
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
        User currentUser = userService.getLoginUser(request);
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


    /**
     * 推荐页面
     * @param request
     * @return
     */
    @GetMapping("/recommend")
    @ApiOperation("推荐页面")
    public BaseResponse<Page<User>> recommendUsers(long pageSize,long pageNum, HttpServletRequest request){
        User loginUser = userService.getLoginUser(request);
        String redisKey = String.format("planets:user:recommend:%s", loginUser.getId());
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        //如果有缓存，直接读缓存
        Page<User> userPage= (Page<User>)valueOperations.get(redisKey);
        if (userPage != null){
            return ResultUtils.success(userPage);
        }
        //无缓存，查数据库
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        userPage = userService.page(new Page<>(pageNum, pageSize), queryWrapper);
        //写缓存
        try {
            valueOperations.set(redisKey,userPage,30000, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            log.error("redis set key error",e);
        }
        return ResultUtils.success(userPage);
    }

    /**
     * 获取最匹配的用户
     *
     * @param num
     * @param request
     * @return
     */
    @GetMapping("/match")
    @ApiOperation("推荐用户")
    public BaseResponse<List<User>> matchUsers(long num, HttpServletRequest request) {
        if (num <= 0 || num > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        return ResultUtils.success(userService.matchUsers(num, user));
    }

    /**
     * 添加标签
     * @param tags
     * @param request
     * @return
     */
    @PostMapping("/addTags")
    @ApiOperation("添加标签")
    public BaseResponse<Boolean> addTags(@RequestBody String tags, HttpServletRequest request){
        if(tags == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        boolean result = userService.addTags(loginUser,tags);
        return  ResultUtils.success(result);
    }

    /**
     * 删除标签
     * @param tags
     * @param request
     * @return
     */
    @PostMapping("/deleteTag")
    @ApiOperation("删除标签")
    public BaseResponse<Boolean> deleteTags(@RequestBody String tags, HttpServletRequest request){
        if(tags == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        boolean result = userService.deleteTag(loginUser,tags);
        return  ResultUtils.success(result);
    }
}

