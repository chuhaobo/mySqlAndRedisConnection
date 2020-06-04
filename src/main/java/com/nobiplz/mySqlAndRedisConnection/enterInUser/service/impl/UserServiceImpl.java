package com.nobiplz.mySqlAndRedisConnection.enterInUser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nobiplz.mySqlAndRedisConnection.enterInUser.entity.UserPo;
import com.nobiplz.mySqlAndRedisConnection.enterInUser.mapper.UserMapper;
import com.nobiplz.mySqlAndRedisConnection.enterInUser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "nobiCache")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserPo> implements UserService   {

  @Autowired
  UserMapper userMapper;


  /**
   * @Cacheable
   * 在方法执行前Spring先查看缓存中是否有数据，如果有，则直接返回缓存数据；若没有，调用方法并将方法返回值放入缓存
   *
   * value：缓存的名称，在 spring 配置文件中定义，必须指定至少一个 例如：
   * @Cacheable(value=”mycache”) 或者
   * @Cacheable(value={”cache1”,”cache2”}
   * key：缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合 例如：
   * @Cacheable(value=”testcache”,key=”#userName”)
   * condition：缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存 例如：
   * @Cacheable(value=”testcache”,condition=”#userName.length()>2”)
   *
   * 作者：一个痴
   * 链接：https://www.jianshu.com/p/7e296d730213
   * 来源：简书
   * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
   * @param userPo
   * @return
   */
  @Override
  @Cacheable(cacheNames = "nobiCache",key = "#userPo.id")
  public Boolean insertUser(UserPo userPo) {
    try{
      userMapper.insert(userPo);
       return true;
    }catch (Exception e){
      e.printStackTrace();
      throw new RuntimeException("添加失败");
    }
  }

  /**
   * @CacheEvict
   * 将一条或者多条数据从缓存中删除
   *
   * value：缓存的名称，在 spring 配置文件中定义，必须指定至少一个 例如：
   * @CachEvict(value=”mycache”) 或者
   * @CachEvict(value={”cache1”,”cache2”}
   * key：缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合 例如：
   * @CachEvict(value=”testcache”,key=”#userName”)
   * condition：缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才清空缓存 例如：
   * @CachEvict(value=”testcache”, condition=”#userName.length()>2”)
   * allEntrie：是否清空所有缓存内容，缺省为 false，如果指定为 true，则方法调用后将立即清空所有缓存 例如：
   * @CachEvict(value=”testcache”,allEntries=true)
   * beforeInvocation：是否在方法执行前就清空，缺省为 false，如果指定为 true，则在方法还没有执行的时候就清空缓存，缺省情况下，如果方法执行抛出异常，则不会清空缓存 例如：
   * @CachEvict(value=”testcache”，beforeInvocation=true)
   *
   * @param userPo
   * @return
   */

  @Override
  @CacheEvict(value = "nobiCache",key = "#userPo.id")
  public Boolean deleteUser(UserPo userPo) {
    try {
      userMapper.deleteById(userPo.getId());
      return null;
    }catch (Exception e){
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   *@Caching
   * 可以通过@Caching注解将多个注解策略组合到一个方法上
   * @Caching注解可以让我们在一个方法或者类上同时指定多个Spring Cache相关的注解。其拥有三个属性：cacheable、put和evict，分别用于指定@Cacheable、@CachePut和@CacheEvict。
   *
   * @param userPo
   * @return
   */
  @Override
  //定义复杂的缓存规则
/*
  @Caching(
    cacheable = {
      @Cacheable(value = "nobiCache",key = "#userPo.id")
    },
    put = {
      //先执行方法
      @CachePut(value = "nobiCache",key = "#userPo.name"),
      @CachePut(value = "nobiCache",key = "#userPo.password")
    }
  )
*/
  @Cacheable(cacheNames = "nobiCache",key = "#userPo.id")
  public UserPo selectUser(UserPo userPo) {
   UserPo userPo1 = userMapper.selectById(userPo.getId());
   return userPo1;
  }

  /**
   * @CachePut：既调用方法又更新缓存; 同步更新缓存
   * 修改了数据库的值，同时更新缓存
   * 运行时机：
   *  1.先调用目标方法
   *  2.将目标方法的结果缓存起来
   *
   * 测试步骤:
   * 1.查询1号，查到结果会放在缓存中
   *      key:1
   * 2.以后查询还是之前的结果
   * 3.更新员工一号 【lastName=老婆】
   * 4.再次查询1号员工
   *      应该是更新后的数据“lastName=老婆”
   *      但是并不是，是之前缓存的数据【1号员工没有在缓存中更新】
   *
   */
  @Override
  @CachePut(value = "nobiCache",key = "#userPo.id")
  public Boolean updateUser(UserPo userPo) {
    try{
      userMapper.updateById(userPo);
      return true;
    }catch (Exception e){
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
  }
}
