package com.nobiplz.mySqlAndRedisConnection.User.po;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserPo implements Serializable {

    //序列化参数 redis存对象必备的
    private static final long serialVersionUID = -5809782578272943999L;

    private String name;

    private Double garade;

    private Integer age;

}
