package com.shok.planets.service;

import com.shok.planets.model.domain.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shok.planets.model.domain.User;
import com.shok.planets.model.dto.TeamQuery;
import com.shok.planets.model.request.TeamJoinRequest;
import com.shok.planets.model.request.TeamQuitRequest;
import com.shok.planets.model.request.TeamUpdateRequest;
import com.shok.planets.model.vo.TeamUserVO;
import com.shok.planets.model.vo.UserVO;

import java.util.List;

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

    /**
     * 查找队伍
     *
     * @param teamQuery
     * @param isAdmin
     * @return
     */
    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    /**
     * 修改队伍
     * @param loginUser
     * @return
     */
    boolean updateTeam(TeamUpdateRequest teamUpdateRequest, User loginUser);

    /**
     * 加入队伍
     * @param teamJoinRequest
     * @param loginUser
     * @return
     */
    boolean joinTeam(TeamJoinRequest teamJoinRequest, User loginUser);

    /**
     * 退出队伍
     * @param teamQuitRequest
     * @param loginUser
     * @return
     */
    boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser);

    /**
     * 解散队伍
     * @param id
     * @param loginUser
     * @return
     */
    boolean deleteTeam(long id, User loginUser);

    /**
     * 设置已加入队伍人数
     * @param teamList
     */
    void setHasJoinNum(List<TeamUserVO> teamList);

    /**
     * 获取队伍成员
     * @param id
     * @return
     */
    List<UserVO> getTeamMembers(Integer teamId);
}
