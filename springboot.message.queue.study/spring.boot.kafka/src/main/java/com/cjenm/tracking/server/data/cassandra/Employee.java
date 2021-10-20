package com.cjenm.tracking.server.data.cassandra;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * <pre>
 * Description : 카산드라 예제 Entity
 *                - @link (@Entity) 와 함께 사용 할 수 없다.
 *                - JPA 전용 Entity 를 만들어햐 할것으로 확인됨
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2019 by CJENM|MezzoMedia. All right reserved.
 * @since 2019-06-07
 */

@Table(value = "employee")
@Getter@Setter
@ToString
public class Employee {

  public Employee() {
  }

  public Employee(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @PrimaryKey
  private int id;
  private String name;
  private String address;
  private String email;
  private int age;



}
