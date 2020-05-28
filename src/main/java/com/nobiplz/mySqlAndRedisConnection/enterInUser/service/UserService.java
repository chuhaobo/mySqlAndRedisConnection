package com.nobiplz.mySqlAndRedisConnection.enterInUser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nobiplz.mySqlAndRedisConnection.enterInUser.entity.UserPo;
import org.springframework.stereotype.Repository;


@Repository
public interface UserService extends IService<UserPo> {

   Boolean insertUser(UserPo userPo);
}
