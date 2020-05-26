package com.nobiplz.mySqlAndRedisConnection.enterInUser.service;

import com.nobiplz.mySqlAndRedisConnection.enterInUser.entity.UserPo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {

   Boolean insertUser(UserPo userPo);
}
