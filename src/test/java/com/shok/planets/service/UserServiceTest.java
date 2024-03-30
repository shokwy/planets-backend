package com.shok.planets.service;

import com.shok.planets.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

/**
 * @author wy
 * @version 1.0
 */
//@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;
    @Test
    void userRegister() {
        String userAccount = "2323";
        String userPassword = "12131390";
        String checkPassword = "12131390";
        //long result = userService.userRegister(userAccount, userPassword, checkPassword);
        //Assertions.assertEquals(-1,result);
    }

    @Test
    void searchUsersByTags() {
        List<String> tagNameList = Arrays.asList("java", "python");
        List<User> userList = userService.searchUsersByTags(tagNameList);
        Assertions.assertNotNull(userList);
    }
}