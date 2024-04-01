package com.shok.planets.service;

import com.shok.planets.model.domain.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shok.planets.model.domain.User;

/**
* @author SHOK
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2024-03-30 22:25:55
*/
public interface TeamService extends IService<Team> {

    /**
     * 创建队伍
     * @param team
     * @param loginUser
     * @return
     */
    long addTeam(Team team, User loginUser);
}
