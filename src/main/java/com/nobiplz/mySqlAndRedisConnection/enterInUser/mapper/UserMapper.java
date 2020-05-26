package com.nobiplz.mySqlAndRedisConnection.enterInUser.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nobiplz.mySqlAndRedisConnection.enterInUser.entity.UserPo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper  extends BaseMapper<UserPo> {

}
