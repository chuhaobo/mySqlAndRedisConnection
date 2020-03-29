package com.nobiplz.mySqlAndRedisConnection.User;

import com.nobiplz.mySqlAndRedisConnection.User.po.UserPo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userContreller {
    public UserPo queryUser(){
            return null;
    }
}
