package com.shok.planets.mapper;

import com.shok.planets.model.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author SHOK
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-03-19 18:53:00
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




