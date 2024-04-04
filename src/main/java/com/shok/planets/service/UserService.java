package com.shok.planets.service;

import com.shok.planets.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shok.planets.model.request.UserRegisterRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户服务
* @author SHOK
* @description 针对表【user】的数据库操作Service
* @createDate 2024-03-19 18:53:00
*/
public interface UserService extends IService<User> {


    /**
     * 用户注册
     * @param userRegisterRequest
     * @return
     */
    long userRegister(UserRegisterRequest userRegisterRequest);

    /**
     * 用户登录
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @return
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     * @param request
     * @return
     */
    Integer userLogout(HttpServletRequest request);

    /**
     * 根据标签搜索用户(内存过滤)
     *
     * @param tagNameList 用户拥有的标签
     * @return
     */
    List<User> searchUsersByTags(List<String> tagNameList);

    /**
     * 用户更新
     * @param user
     * @return
     */
    int updateUser(User user, User loginUser);

    /**
     * 当前登录用户是否为管理员
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 判断用户是否为管理员
     * @param user
     * @return
     */
    boolean isAdmin(User user);

    /**
     * 获取当前登录用户
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 匹配用户
     * @param num
     * @param user
     * @return
     */
    List<User> matchUsers(long num, User user);

    /**
     * 添加标签
     * @param loginUser
     * @param tags
     * @return
     */
    boolean addTags(User loginUser, String tags);

    /**
     * 删除标签
     * @param loginUser
     * @param tags
     * @return
     */
    boolean deleteTag(User loginUser, String tags);
}
