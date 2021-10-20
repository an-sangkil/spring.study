package com.cjenm.tracking.server.repository.jpa;

import com.cjenm.tracking.server.data.cassandra.Employee;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

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
public interface EmployeeJpaRepository  extends ReactiveCassandraRepository<Employee,Integer> {

}
