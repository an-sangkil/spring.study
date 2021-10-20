package com.cjenm.tracking.server.web.handler;

import com.cjenm.tracking.server.data.cassandra.Employee;
import com.cjenm.tracking.server.service.EmployeeService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <pre>
 * Description : 핸들러 예제
 *                  1) 카산드라 연동 예제
 *                  2) WebFlux Exception handling 방법 예제
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|MezzoMedia. All right reserved.
 * @since 2019-06-07
 */
@Component
@Slf4j
public class EmployeeHandler {

  private EmployeeService employeeService;

  public EmployeeHandler(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  public Mono<ServerResponse> findAllEmployee(ServerRequest request) {
    var dataStringTest = "1";
    var dataIntegerTest = 1;

    Flux<Employee> employeeFlux = employeeService.findAllEmployee();
    return ServerResponse.ok().body(employeeFlux, Employee.class);
  }

  public Mono<ServerResponse> saveEmployee(ServerRequest serverRequest) {
    Mono<String> responseMessage = Mono.empty();
    Mono<Employee> employeeMono = Mono.empty();

    String userName = serverRequest.queryParam("userName").get();
    int id = serverRequest.queryParam("id").flatMap(s -> Optional.of(Integer.parseInt(s))).orElseGet(() -> 0);
    int age = serverRequest.queryParam("age").flatMap(s -> Optional.of(Integer.parseInt(s))).orElseGet(() -> 0);
    Employee saveEmployee = new Employee();
    saveEmployee.setId(id);
    saveEmployee.setAge(age);
    saveEmployee.setName(userName);

    employeeMono = this.employeeService.saveEmployee(saveEmployee);
    //mono.doOnError(RuntimeException::new).subscribe();
    log.debug("save date = ", employeeMono);

    return employeeMono
        .flatMap(data -> ServerResponse.ok().syncBody(data))
        .onErrorResume(e -> Mono.just("{ status : error }").flatMap(s -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).syncBody(s)));

        /*ServerResponse.ok().body(employeeMono
            .onErrorReturn()
            .doOnError(throwable -> log.error("saveEmployee error = {} ", throwable))
            .flatMap(ep -> Mono.just("{ status :  success }")), String.class);*/

  }


  public Mono<ServerResponse> fallbackExcpotionCase1(ServerRequest serverRequest) {
    Mono<Employee> employeeMono = Mono.empty();
    if (serverRequest.queryParam("type").get().equals("1")) {
      // 글로벌 익셉션 오류 이며 Mono 에러로 잡히지 않음
      throw new RuntimeException("");
    }

    // 강제로 Mono error Exception 발생
    employeeMono = this.employeeService.monoException();

    return employeeMono
        .flatMap(data -> ServerResponse.ok().syncBody(data))
        .onErrorResume(e -> Mono.just("{ status : error }")
            .flatMap(s -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).syncBody(s)));
  }

  public Mono<ServerResponse> fallbackExcpotionCase2(ServerRequest serverRequest) {
    Mono<Employee> employeeMono = Mono.empty();

    if (serverRequest.queryParam("type").get().equals("1")) {
      // 글로벌 익셉션 오류 이며 Mono 에러로 잡히지 않음
      throw new RuntimeException("");
    }

    // 강제로 Mono error Exception 발생
    employeeMono = this.employeeService.monoException();

    return employeeMono
        .flatMap(data -> ServerResponse.ok().syncBody(data))
        .onErrorResume(e -> employeeFallback());

  }

  public Mono<ServerResponse> fallbackExcpotionCase3(ServerRequest serverRequest) {
    Mono<Employee> employeeMono = Mono.empty();

    if (serverRequest.queryParam("type").get().equals("1")) {
      // 글로벌 익셉션 오류 이며 Mono 에러로 잡히지 않음
      throw new RuntimeException("");
    }

    // 강제로 Mono error Exception 발생
    employeeMono = this.employeeService.monoException();

    return ServerResponse.ok()
        .body(employeeMono.onErrorResume(e -> Mono.error(new WebClientResponseException(HttpStatus.BAD_REQUEST.value(), "", null, null, null))), Employee.class);
  }

  private Mono<ServerResponse> employeeFallback() {
    return ServerResponse.ok().body(Mono.just("{\"status\" : \"common error employeeFallback\"}"), String.class);
  }

}
