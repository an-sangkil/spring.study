package com.cjenm.tracking.server.service;

import com.cjenm.tracking.server.data.cassandra.Employee;
import com.cjenm.tracking.server.repository.jpa.EmployeeJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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
 * @since 2019-06-07
 */
@Service
@Slf4j
public class EmployeeService {

  private EmployeeJpaRepository employeeJpaRepository;

  public EmployeeService(EmployeeJpaRepository employeeJpaRepository) {
    this.employeeJpaRepository = employeeJpaRepository;
  }

  public Flux<Employee> findAllEmployee() {
    return employeeJpaRepository.findAll();
  }

  public Mono<Employee> saveEmployee(Employee employee) {
    return employeeJpaRepository.save(employee);
  }

  public Flux<Employee> saveEmployee(Mono<Employee> employees) {
    return employeeJpaRepository.insert(employees);
  }
  public Flux<Employee> saveEmployeeBatch(Flux<Employee> employees) {
    return employeeJpaRepository.insert(employees);
  }

  /**
   * Mono 에러 테스트
   */
  public Mono<Employee> monoException() {
    return Mono.just(new Employee(1, "test name"))
        .doOnNext(
            s -> {
              log.info("next data = {}", s.toString());

              // 강제 에러 발생
              throw new RuntimeException("Error check ");
            }

        );
  }

  ;

}
