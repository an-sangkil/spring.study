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

    /**
     * 서킷 브레이크레이커에 의해 실패된 요청이 있을경우 이쪽으로 리다이렉트된다.
     *  - 실패된 내용에 대한 응답을 준다.
     *
     * @return
     */
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
