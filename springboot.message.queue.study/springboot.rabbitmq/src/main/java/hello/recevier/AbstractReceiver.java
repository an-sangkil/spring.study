package hello.recevier;

import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2018 by skan. All right reserved.
 * @since 2019-01-08
 */
@Slf4j
public abstract class AbstractReceiver {

  protected CountDownLatch latch = new CountDownLatch(1);

  abstract public void receiveMessage(String message) throws InterruptedException;

  public CountDownLatch getLatch() {

    log.trace("RECEIVER COUNT LATCH = {} ", latch.getCount());
    
    return latch;
  }

}
