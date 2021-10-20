package hello.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import hello.recevier.EventReceiverTwo;

/**
 * 
 * Description : Rabbit MQ , Configuration 설정
 *                - event Queue  
 *
 * @author skan
 * @since 2019. 10. 10.
 * @version 
 *
 * Copyright (C) 2019 by skan.Inc. All right reserved.
 */
@Configuration
public class RabbitMQEventConfig {


  public static final String topicExchangeName = "spring-boot-adteck";

  public static final String queueName = "db-event";

  @Bean
  public Queue eventQueue() {
    return new Queue(queueName, false);
  }

  @Bean
  public TopicExchange eventExchange() {
    return new TopicExchange(topicExchangeName);
  }

  @Bean(value="eventBinding")
  public Binding binding(Queue eventQueue, TopicExchange eventExchange) {
    return BindingBuilder.bind(eventQueue).to(eventExchange).with("com.event.#");
  }

  @Bean
  SimpleMessageListenerContainer eventContainer(ConnectionFactory connectionFactory, @Qualifier("eventListenerAdapter") MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  @Bean("eventListenerAdapter")
  MessageListenerAdapter listenerAdapter2(EventReceiverTwo EventReceiverTwo) {
    return new MessageListenerAdapter(EventReceiverTwo, "receiveMessage");
  }


}
