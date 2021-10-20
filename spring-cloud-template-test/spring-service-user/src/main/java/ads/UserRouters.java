package ads;

import io.netty.util.internal.ConstantTimeUtils;
import org.springframework.context.annotation.Bean;
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
public class UserRouters {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserHandler userHandler) {
        return
                RouterFunctions.route(RequestPredicates.GET("/user/get_all")
                        .and(RequestPredicates.accept(MediaType.ALL)), request -> {

                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    return ServerResponse.ok().body(Mono.just("hello user get all"), String.class);
                }).and(
                        RouterFunctions.route(RequestPredicates.GET("/user/userId"), request -> {
                            String userId = (String) request.attribute("userId").orElseGet(() -> "");
                            System.out.println(userId);
                            Mono<String> data = Mono.just("you search , user id equal =" + userId);
                            return ServerResponse.ok().body(data, String.class);
                        }))
                        .andRoute(RequestPredicates.GET("/user/save"), userHandler::save)
                        .andRoute(RequestPredicates.GET("/user/delete"), request -> {
                            Mono<String> bodyData = request.bodyToMono(String.class);
                            return ServerResponse.ok().body(bodyData, String.class);
                        })


                ;
    }

}
