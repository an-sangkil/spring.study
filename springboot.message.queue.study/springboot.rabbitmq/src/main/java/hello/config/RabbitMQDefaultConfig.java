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
import hello.recevier.DefaultReceiver;

/**
 * 
 * Description : Rabbit MQ , Configuration 설정
 *                - 기본 defaultQueue  
 *
 * @author skan
 * @since 2019. 10. 10.
 * @version 
 *
 * Copyright (C) 2019 by skan.Inc. All right reserved.
 */
@Configuration
public class RabbitMQDefaultConfig {

  public static final String topicExchangeName = "spring-boot-exchange";
  public static final String queueName = "spring-boot";

  @Bean
  public Queue defaultQueue() {
    // 
    return new Queue(queueName, false);
  }

  @Bean
  public TopicExchange defaultExchange() {
    return new TopicExchange(topicExchangeName);
  }

  @Bean(value="defaultBinding")
  public Binding binding(Queue defaultQueue, @Qualifier("defaultExchange") TopicExchange exchange) {
    return BindingBuilder.bind(defaultQueue).to(exchange).with("com.adteck.#");
  }

  @Bean
  public SimpleMessageListenerContainer defaultContainer(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  @Bean
  public MessageListenerAdapter listenerAdapter(DefaultReceiver defaultReceiver) {
    return new MessageListenerAdapter(defaultReceiver, "receiveMessage");
  }


}
