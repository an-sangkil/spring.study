package com.cjenm.tracking.server.web.handler;

import com.cjenm.tracking.server.service.KafkaProducerService;
import java.util.stream.IntStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|MezzoMedia. All right reserved.
 * @since 2019-10-07
 */
@Component
public class KafkaHandler {

  private KafkaProducerService kafkaProducerService;

  public static final String TOPIC_NAME = "advertising";


  public KafkaHandler(KafkaProducerService kafkaProducerService) {
    this.kafkaProducerService = kafkaProducerService;
  }

  @Bean
  public RouterFunction<ServerResponse> kafkaRouterFunction(KafkaHandler kafkaHandler) {
    return RouterFunctions.route(RequestPredicates.path("/kafka/sender1"), kafkaHandler::producer)
        ;
  }

  private Mono<ServerResponse> producer(ServerRequest serverRequest) {

    IntStream.rangeClosed(1, 100).parallel().forEach(value -> {
      kafkaProducerService.sender(TOPIC_NAME, "data" + value);
    });

    return ServerResponse.ok().syncBody("{code:SUCCESS}");
  }


}


