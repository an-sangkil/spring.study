package gateway.router;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
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
 * @version Copyright (C) 2021 by CJENM|MezzoMedia. All right reserved.
 * @since 2021-06-21
 */
@Component
public class GatewayRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route().path("/fallback", builder -> builder
                .GET("/default", RequestPredicates.accept(MediaType.ALL), request -> {


                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(APIResponse.builder().message("this is fallback default message").build()), APIResponse.class);
                }))

                .build();
    }

    @Data
    public static class APIResponse {
        private final String status;
        private final String data;
        private final String message;

        @Builder
        public APIResponse(String status, String data, String message) {
            this.status = status;
            this.data = data;
            this.message = message;
        }
    }

}
