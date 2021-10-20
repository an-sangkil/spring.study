package hello;

import hello.config.RabbitMQDefaultConfig;
import hello.config.RabbitMQEventConfig;
import hello.recevier.EventReceiverTwo;
import hello.recevier.DefaultReceiver;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Runner implements CommandLineRunner {

  private final RabbitTemplate rabbitTemplate;
  
  private final hello.recevier.DefaultReceiver defaultReceiver;
  private final hello.recevier.EventReceiverTwo dbEventReceiver;

  public Runner(DefaultReceiver defaultReceiver, RabbitTemplate rabbitTemplate, EventReceiverTwo dbEventReceiver) {
    this.defaultReceiver = defaultReceiver;
    this.rabbitTemplate = rabbitTemplate;
    this.dbEventReceiver = dbEventReceiver;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Sending message...");

    IntStream.rangeClosed(1, 100).forEach(value -> {

          log.debug("data = {}", value);
          
          rabbitTemplate.convertAndSend(RabbitMQDefaultConfig.topicExchangeName, "com.adteck.dbevent", "Hello from RabbitMQ! DEFAULT (" + value + ")");
          rabbitTemplate.convertAndSend(RabbitMQEventConfig.topicExchangeName, "com.event.test", "Hello from RabbitMQ! EVENT(" + value + ")");

          try {
            //dbEventReceiver.getLatch().await(1, TimeUnit.SECONDS);
            defaultReceiver.getLatch().await(10, TimeUnit.MILLISECONDS);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

        }
    );
  }



}
