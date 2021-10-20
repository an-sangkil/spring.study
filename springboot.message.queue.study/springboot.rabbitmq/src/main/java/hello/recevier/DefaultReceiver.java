package hello.recevier;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

/**
 * 
 * <pre>
 * 
 * Description : 구독자 기능 수행 
 *               Queue 사용 클레스 (Default Receiver - 1 번)
 *               Queue Name : default 
 *               Exchange Type :  
 * </pre>
 *
 * @author skan
 * @since 2019. 10. 10.
 * @version 
 *
 * Copyright (C) 2019 by skan.Inc. All right reserved.
 */
@Component
@Slf4j
public class DefaultReceiver  extends AbstractReceiver{

  public void receiveMessage(String message) throws InterruptedException {

    System.out.println("Default Received -1  <" + message + ">");
    //TimeUnit.SECONDS.sleep(5);
    super.latch.countDown();
  }



}
