package com.shok.planets.mapper;

import com.shok.planets.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author wy
 * @version 1.0
 */
@SpringBootTest
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void test(){
        User user = new User();
        user.setUsername("shok");
        user.setUserAccount("shok123");
        user.setAvatarUrl("https://images.zsxq.com/FnmLJVbrKI1EQ-PcwwuJhzfhQ4z_?e=1714492799&token=kIxbL07-8jAj8w1n4s9zv64FuZZNEATmlU_Vm6zD:OObdGrntcXGZVJXrotl_2jeNeuI=");
        user.setGender(0);
        user.setUserPassword("123");
        user.setPhone("345");
        user.setEmail("567");
        user.setIntroduction("加油");

        int insert = userMapper.insert(user);
        System.out.println(user.getId());
        Assertions.assertEquals(1,insert);
    }
}