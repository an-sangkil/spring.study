package hello.recevier;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import hello.code.RabbitConst;

/**
 * <pre>
 * Description : 구독자 역할 수행
 *               Queue 사용 클레스 (Event Receiver - 1번)
 *               Queue Name : db-event
 *               Exchange Type :  
 *
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by skan. All right reserved.
 * @since 2019-10-10
 */
@Component
@RabbitListener(queues = {RabbitConst.QUEUE_NAME})
@Slf4j
public class EventReceiver extends AbstractReceiver {

    
  @RabbitHandler
  @Override
  public void receiveMessage(String message) {
    System.out.println("Event Received - 1  <" + message + ">");
    super.latch.countDown();
  }

}
