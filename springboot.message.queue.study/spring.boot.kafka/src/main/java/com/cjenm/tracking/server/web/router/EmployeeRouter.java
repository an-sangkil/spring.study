package com.cjenm.tracking.server.web.router;

import com.cjenm.tracking.server.web.handler.EmployeeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * <pre>
 * Description : 예제 라우터
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|MezzoMedia. All right reserved.
 * @since 2019-06-07
 */
@Component
public class EmployeeRouter {

  @Bean
  public RouterFunction<?> routerFunction(EmployeeHandler employeeHandler) {
    return RouterFunctions.route(RequestPredicates.GET("/employee/findAll"),employeeHandler::findAllEmployee)
        .andRoute(GET("/employee/save"),employeeHandler::saveEmployee)
        .andRoute(GET("/employee/fallback1"),employeeHandler::fallbackExcpotionCase1)
        .andRoute(GET("/employee/fallback2"),employeeHandler::fallbackExcpotionCase2)
        .andRoute(GET("/employee/fallback3"),employeeHandler::fallbackExcpotionCase3);
  }

}
