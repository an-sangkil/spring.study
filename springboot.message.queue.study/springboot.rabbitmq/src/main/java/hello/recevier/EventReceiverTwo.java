package hello.recevier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * <pre>
 * Description : 구독자 역할 수행
 *               Queue 사용 클레스 (Event Receiver - 2번)
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
@Slf4j
public class EventReceiverTwo extends AbstractReceiver {

  public void receiveMessage(String message) throws InterruptedException {
    System.out.println("Event Received - 2  <" + message + ">");
    super.latch.countDown();

  }



}
