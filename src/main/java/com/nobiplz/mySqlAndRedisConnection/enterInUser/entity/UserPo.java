package com.nobiplz.mySqlAndRedisConnection.enterInUser.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("user")
public class UserPo implements Serializable {

  private static final long serialVersionUID = -3946734305303957850L;


  @TableField("id")
  private String id;

  @TableField("name")
  private String name;


  @TableField("password")
  private String password;

}
