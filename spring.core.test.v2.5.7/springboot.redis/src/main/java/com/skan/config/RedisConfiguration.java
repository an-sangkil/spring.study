package com.skan.config;

import java.net.UnknownHostException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

  @Bean
  public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());

    return redisTemplate;
  }

  @Bean
  public StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
    return new StringRedisTemplate(connectionFactory);
  }

  @Bean
  public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    return container;
  }
}
