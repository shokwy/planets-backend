package com.shok.planets.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shok.planets.model.domain.UserTeam;
import com.shok.planets.service.UserTeamService;
import com.shok.planets.mapper.UserTeamMapper;
import org.springframework.stereotype.Service;

/**
* @author SHOK
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2024-03-30 22:26:42
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService{

}




