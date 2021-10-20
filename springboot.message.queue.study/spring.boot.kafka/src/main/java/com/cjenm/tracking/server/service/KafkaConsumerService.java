package com.cjenm.tracking.server.service;

import com.cjenm.tracking.server.web.handler.KafkaHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Description : 메세지 구독자
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
public class KafkaConsumerService {

  /**
   * 카프카 파티션이 0,1 번인 것만 리슨, 시작시 바로 실행
   * @param consumerRecord
   */
  @KafkaListener(
      groupId = "report-1",
      id = "id-report-1",
      topicPartitions = {
          @TopicPartition(topic = KafkaHandler.TOPIC_NAME, partitions = {"0", "1"})
      },
      autoStartup = "true"
  )
  public void receiveTopic1(ConsumerRecord consumerRecord) {
    log.info("receive on topic - 1 = {}", consumerRecord.toString());

  }

  /**
   * 카프카 파티션이 1,2,3 번인 것만 리슨, 시작시 바로 실행
   * @param consumerRecord
   */
  @KafkaListener(
      groupId = "report-1",
      id = "id-report-2",
      topicPartitions = {
          @TopicPartition(topic = KafkaHandler.TOPIC_NAME, partitions = {"1"}),
          @TopicPartition(topic = KafkaHandler.TOPIC_NAME, partitions = {"2", "3"})
      },
      autoStartup = "true"

  )
  public void receiveTopic2(ConsumerRecord consumerRecord) {
    log.info("receive on topic - 2 = {}", consumerRecord.toString());
  }

  /**
   * 카프카 파티션이 4,5 번인 것만 리슨, 시작시 바로 실행
   * @param consumerRecord
   */
  @KafkaListener(
      groupId = "report-1",
      id = "id-report-3",
      topicPartitions = {
          @TopicPartition(topic = KafkaHandler.TOPIC_NAME, partitions = {"4","5"}),
      },
      autoStartup = "true"

  )
  public void receiveTopic5(ConsumerRecord consumerRecord) {
    log.info("receive on topic - 5 = {}", consumerRecord.toString());

  }


}
