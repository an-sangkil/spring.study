package skan.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import skan.annotation.Retry;

import java.util.Random;

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

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    public String save(String data) {
        log.info("data = {}", data);
        return "DATA SAVE SUCCESS";
    }

    public int delete(String data) throws Exception{
        Random random = new Random();
        // try catch block 에 의해 aspectThrowing 은 동작하지 안는다.
        // try-catch block 을 삭제 하면 다시 aspect 에 검사된다.
        //try {
            if (random.nextBoolean()) {
                throw new RuntimeException("delete 예외발생");
            }
            log.info("delete 성공 !!! ");
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        return 0;
    }

}
