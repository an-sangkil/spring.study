package com.skan.pubsub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 구독자
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class RedisSubscriber implements MessageListener {

    private final RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String body = (String) redisTemplate
                    .getStringSerializer()
                    .deserialize(message.getBody());

            log.info("message = {}", body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
