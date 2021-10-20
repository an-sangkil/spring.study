package com.cjenm.tracking.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * <pre>
 * Description : 메세지 발행
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|MezzoMedia. All right reserved.
 * @since 2019-10-07
 */
@Service
@Slf4j
public class KafkaProducerService {

  private KafkaTemplate<String, String> kafkaTemplate;

  public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sender(String topic, String data) {

    ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic, data);
    listenableFuture.addCallback(result -> {
      log.trace("send message success = {}", data);
    }, ex -> {
      //throw new RuntimeException("error");
      log.error("send to message fail");
    });

  }

}
