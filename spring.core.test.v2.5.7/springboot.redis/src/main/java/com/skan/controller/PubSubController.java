package com.skan.controller;

import com.skan.pubsub.RedisPublisher;
import com.skan.pubsub.RedisSubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.bind.annotation.*;

/**
 * 참고 :  https://daddyprogrammer.org/post/3688/redis-spring-data-redis-publish-subscribe/
 */
@RequiredArgsConstructor
@RestController
public class PubSubController {

    // topic에 발행되는 액션을 처리할 Listner
    private final RedisMessageListenerContainer redisMessageListener;
    private final RedisPublisher publisher;
    private final RedisSubscriber subscriber;

    // 특정 topic에 메시지를 발송할 수 있도록 topic정보를 Map에 저장
    private Map<String, ChannelTopic> channels;

    @PostConstruct
    public void init() {
        // 실행될때 topic정보를 담을 Map을 초기화
        channels = new HashMap<>();
    }

    /**
     * 유효한 채널(토픽) 정보 반환
     */
    @GetMapping(value = "/room/find-all")
    public Set<String> getMethodName() {
        return channels.keySet();
    }

    /**
     * 채널(토픽)을 생성하여 Listener에 등록한뒤 방아이디를 찾을 Topic Channels(Map 데이터)에 저장 한다.
     */
    @PutMapping(value = "/room/{roomId}")
    public void createRoom(@PathVariable String roomId) {
        ChannelTopic channelTopic = new ChannelTopic(roomId);
        redisMessageListener.addMessageListener(subscriber, channelTopic);
        channels.put(roomId, channelTopic);
    }

    @PostMapping(value = "/room/send-message/{roomId}")
    public void postMethodName(
            @PathVariable String roomId,
            @RequestParam String name,
            @RequestParam String message
    ) {
        ChannelTopic channelTopic = channels.get(roomId);
        this.publisher.sendMessage(channelTopic, message);
    }

    @DeleteMapping(value = "/room/{roomId}")
    public void deleteRoom(@PathVariable String roomId) {
        ChannelTopic channelTopic = channels.get(roomId);
        redisMessageListener.removeMessageListener(subscriber, channelTopic);
        channels.remove(roomId);
    }
}
