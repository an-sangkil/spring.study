package reactor.test.config;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.test.controller.DefaultHandler;

@Configuration
public class RouterConfig {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 라우더 Mapping 중앙 관리 
	 * @param defaultHandler
	 * @return
	 */
	@Bean
	public RouterFunction<ServerResponse> hello3(DefaultHandler defaultHandler) {
		
		logger.info("Configuration RouterFunction create !!!");
		
		return route(RequestPredicates.GET("/hello3/{name}"), defaultHandler::hello3)
				.andRoute(path("/hello4/{name}"), defaultHandler::hello3);
		
	}
}
