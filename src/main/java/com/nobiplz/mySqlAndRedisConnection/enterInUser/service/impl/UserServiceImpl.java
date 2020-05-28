package com.nobiplz.mySqlAndRedisConnection.enterInUser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nobiplz.mySqlAndRedisConnection.enterInUser.entity.UserPo;
import com.nobiplz.mySqlAndRedisConnection.enterInUser.mapper.UserMapper;
import com.nobiplz.mySqlAndRedisConnection.enterInUser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserPo> implements UserService   {

  @Autowired
  UserMapper userMapper;

  @Override
  public Boolean insertUser(UserPo userPo) {
    try{
      userMapper.insert(userPo);
       return true;
    }catch (Exception e){
      e.printStackTrace();
      throw new RuntimeException("添加失败");
    }
  }
}
