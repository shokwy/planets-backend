package com.shok.planets.mapper;

import com.shok.planets.model.domain.Friends;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shok.planets.model.domain.User;

/**
* @author SHOK
* @description 针对表【friends(好友申请管理表)】的数据库操作Mapper
* @createDate 2024-04-08 18:31:24
* @Entity com.shok.planets.model.domain.Friends
*/
public interface FriendsMapper extends BaseMapper<Friends> {

    /**
     * 判断是否为好友
     * @param id
     * @param userId
     * @return
     */
    int isFriend(Long id, Long userId);
}




