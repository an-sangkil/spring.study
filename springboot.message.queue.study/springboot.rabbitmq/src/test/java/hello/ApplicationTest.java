/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hello;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;
import hello.config.RabbitMQDefaultConfig;
import hello.config.RabbitMQEventConfig;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class ApplicationTest {

  @MockBean
  private Runner runner;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private hello.recevier.DefaultReceiver defaultReceiver;

  @Test
  public void test() throws Exception {

    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    IntStream.rangeClosed(1, 10000).forEach(value -> {

          rabbitTemplate.convertAndSend(RabbitMQDefaultConfig.topicExchangeName, "com.adteck.dbevent", "Hello from RabbitMQ! (" + value + ")");
          rabbitTemplate.convertAndSend(RabbitMQEventConfig.topicExchangeName, "com.event.test", "Hello from RabbitMQ! EVENT(" + value + ")");

          try {
            defaultReceiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
    );

    stopWatch.stop();

    System.out.println(stopWatch.prettyPrint());
    log.info("완료 시간  = {}" , stopWatch.prettyPrint());



  }

}
