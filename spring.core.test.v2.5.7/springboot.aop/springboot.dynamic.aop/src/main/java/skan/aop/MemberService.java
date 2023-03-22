package skan.aop;

import org.springframework.stereotype.Service;
import skan.annotation.Retry;

import java.lang.annotation.Inherited;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2022 by CJENM|MezzoMedia. All right reserved.
 * @since 2022/03/23
 */
public interface MemberService {

    @Retry
    String save(String test_data);
    @Retry(1)
    int delete(String data) throws Exception;
}
