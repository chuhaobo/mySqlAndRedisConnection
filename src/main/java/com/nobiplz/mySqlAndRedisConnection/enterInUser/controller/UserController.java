package com.nobiplz.mySqlAndRedisConnection.enterInUser.controller;

import com.nobiplz.mySqlAndRedisConnection.enterInUser.entity.UserPo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "人员操作接口", description = "人员操作接口")
@RequestMapping("/userOperation")
public class UserController {


  public boolean insertUser(UserPo userPo){
    try{

    }catch (Exception e){
      throw  new RuntimeException(e.getMessage());
    }
  }

}
