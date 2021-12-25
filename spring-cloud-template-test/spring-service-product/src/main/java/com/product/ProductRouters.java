package com.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2021 by CJENM|MezzoMedia. All right reserved.
 * @since 2021-06-17
 */
@Component
@Slf4j
public class ProductRouters {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(ProductHandler productHandler) {
        return
                RouterFunctions.route(RequestPredicates.GET("/product/get_all")
                        .and(RequestPredicates.accept(MediaType.ALL)), request -> {

                    try {
                        // 테스트를 위한 지연시간 4 초 , 게이트웨이 설정 시간 3초
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    return ServerResponse.ok().body(Mono.just("hello user get all"), String.class);
                }).and(
                        RouterFunctions.route(RequestPredicates.GET("/product/productId"), request -> {
                            String userId = (String) request.attribute("productId").orElseGet(() -> "");
                            System.out.println(userId);
                            Mono<String> data = Mono.just("you search , product id equal =" + userId);
                            return ServerResponse.ok().body(data, String.class);
                        }))
                        .andRoute(RequestPredicates.GET("/product/save"), productHandler::save)
                        .andRoute(RequestPredicates.GET("/product/delete"), request -> {
                            Mono<String> bodyData = request.bodyToMono(String.class);
                            return ServerResponse.ok().body(bodyData, String.class);
                        })
                .and(
                        RouterFunctions.route(RequestPredicates.GET("/product/notfound_error"),request -> {
                            log.info("/product/notfound_error call");

                            return ServerResponse.status(HttpStatus.BAD_GATEWAY).body(Mono.just("dd"),String.class);
                        })
                )


                ;
    }

}
