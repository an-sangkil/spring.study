package reactor.test.controller;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 
 *
 * @author skan
 * @since 2018. 6. 8.
 * @version 
 *
 * Copyright (C) 2018 by skan.Inc. All right reserved.
 */
@RestController
public class DefaultController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 일반 requestMapping 방식 
	 * @param name
	 * @return
	 */
	@GetMapping("/hello/{name}")
	public String helloTest(@PathVariable String name) {
		return "Hello" + name;
		
	}
	/**
	 * 라우터 핸들러 함수형식 방식 
	 * @return
	 */
	@Bean
	public RouterFunction<ServerResponse> helloPathVarRouter() {
		
		logger.info("helloPathVarRouter");
		
		return route(path("/hello2/{name}"), req -> ok().body(BodyInserters.fromObject("hello2" + req.pathVariable("name"))));
	}

}
