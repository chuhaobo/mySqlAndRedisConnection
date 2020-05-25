package com.nobiplz.mySqlAndRedisConnection.enterInUser.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nobiplz.mySqlAndRedisConnection.enterInUser.entity.UserPo;
import com.nobiplz.mySqlAndRedisConnection.enterInUser.service.UserService;

public class UserServiceImpl implements UserService  {
  @Override
  public Boolean insertUser(UserPo userPo) {
    try{

    }catch (Exception e){
      e.printStackTrace();;
      throw new RuntimeException("添加失败");
    }
  }
}
