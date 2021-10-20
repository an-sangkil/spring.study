package reactor.test.controller;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import reactor.test.config.RouterConfig;
import reactor.test.service.DefaultService;

/**
 * 
 * 별도 핸들러 클레스  
 *  => 핸들러와 라우터 함수 분리  
 * 
 * @author skan
 * @since 2018. 6. 8.
 * @version 
 *
 * Copyright (C) 2018 by skan.Inc. All right reserved.
 */
@Component
public class DefaultHandler {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired DefaultService defaultService;
	
	public Mono<ServerResponse> hello3(ServerRequest request) {
		
		String name = request.pathVariable("name");
		String returnData = defaultService.hello3(name);
		
		return ServerResponse.ok().body(BodyInserters.fromObject(returnData));
	}
	
	
	public Mono<ServerResponse> hello4(ServerRequest request) {
		
		String name = request.pathVariable("name");
		String returnData = defaultService.hello4(name);
		
		return ServerResponse.ok().body(BodyInserters.fromObject(returnData));
	}
	
	
	
	/**
	 * 라우터 매핑 분리 관리  
	 * {@link RouterConfig} 에서 통합 관리 대상으로 사용하여도 됨 
	 *  테스트를 위해 생성된 Router Bean 함수   
	 * 
	 * @param defaultHandler
	 * @return
	 */
	@Bean
	public RouterFunction<ServerResponse> varhello3(DefaultHandler defaultHandler) {
		
		logger.info("Configuration RouterFunction create !!!");
		
		return route(RequestPredicates.GET("/distrebute/{name}"), defaultHandler::hello3)
				.andRoute(RequestPredicates.POST("/distrebute/{name}"), defaultHandler::hello4);
		
	}

}
