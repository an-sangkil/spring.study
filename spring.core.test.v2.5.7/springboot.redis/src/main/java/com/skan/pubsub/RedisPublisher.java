package com.skan.pubsub;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

/**
 * 메세지 생성자
 */
@RequiredArgsConstructor
@Service
public class RedisPublisher {

    private final RedisTemplate<Object, Object> redisTemplate;

    /**
     * 채널(토픽) 으로 메세지를 생성해서 보낸다.
     */
    public void sendMessage(ChannelTopic channelTopic, String message) {
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }
}

