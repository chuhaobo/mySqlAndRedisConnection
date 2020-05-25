package com.nobiplz.mySqlAndRedisConnection.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig {

  //设置过期时间
  //Duration 为时间间隔
  private Duration  timeValue = Duration.ofDays(1);

  @Bean(name = "RedisTemplate")
  public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
    RedisTemplate<Object,Object> template = new RedisTemplate<>();
    //设置为当前redis链接
    template.setConnectionFactory(redisConnectionFactory);
    template.setKeySerializer(keySerializer());
    template.setHashKeySerializer(keySerializer());
    template.setValueSerializer(valueSerializer());
    template.setHashValueSerializer(valueSerializer());
    return template;
  }

  private RedisSerializer<String> keySerializer() {
    return new StringRedisSerializer();
  }
  private RedisSerializer<Object> valueSerializer() {
    return new GenericJackson2JsonRedisSerializer();
  }

  @Bean
  public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    //默认1
    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
      //设置过期时间
      .entryTtl(this.timeValue)
      .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
      .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
      .disableCachingNullValues();
    RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
      .cacheDefaults(config)
      .transactionAware()
      .build();
    return redisCacheManager;
  }
}
