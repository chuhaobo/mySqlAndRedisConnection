package com.nobiplz.mySqlAndRedisConnection.enterInUser.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
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
public class UserController  extends ApiController {

  @Autowired
  UserService userService;

  @ApiOperation(value = "新增user", httpMethod = "POST")
  @RequestMapping(value = "insertUser",method = RequestMethod.POST)
  public boolean insertUser(UserPo userPo){
    try{
       return userService.insertUser(userPo);
    }catch (Exception e){
      throw  new RuntimeException(e.getMessage());
    }
  }

  @ApiOperation(value = "删除User",httpMethod = "DELETE")
  @RequestMapping(value = "deleteUser",method = RequestMethod.DELETE)
  public  Boolean deleteUser(UserPo userPo){
    try{
      return userService.deleteUser(userPo);
    }catch (Exception e){
      throw new RuntimeException(e.getMessage());
    }
  }

  @ApiOperation(value = "查询User",httpMethod = "GET")
  @RequestMapping(value = "selectUser",method = RequestMethod.GET)
  public  UserPo selectUser(UserPo userPo){
    try{
      return userService.selectUser(userPo);
    }catch (Exception e){
      throw new RuntimeException(e.getMessage());
    }
  }

  @ApiOperation(value = "修改User",httpMethod = "POST")
  @RequestMapping(value = "updateUser",method = RequestMethod.POST)
  public  Boolean updateUser(UserPo userPo){
    try{
      return userService.updateUser(userPo);
    }catch (Exception e){
      throw new RuntimeException(e.getMessage());
    }
  }
}
