package com.nobiplz.mySqlAndRedisConnection.enterInUser.controller;

import com.nobiplz.mySqlAndRedisConnection.enterInUser.entity.UserPo;
import com.nobiplz.mySqlAndRedisConnection.enterInUser.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "人员操作接口", description = "人员操作接口")
@RequestMapping("/userOperation")
public class UserController {

  @Autowired
  UserService userService;

  @ApiOperation(value = "新增user", httpMethod = "GET")
  @RequestMapping(value = "insertUser",method = RequestMethod.POST)
  public boolean insertUser(UserPo userPo){
    try{
       return userService.insertUser(userPo);
    }catch (Exception e){
      throw  new RuntimeException(e.getMessage());
    }
  }

}
