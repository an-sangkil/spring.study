package skan.spring.cglib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2022 by CJENM|MezzoMedia. All right reserved.
 * @since 2022/03/25
 */

@Service
class MemberService {
    @Async
    void save(String data){
        System.out.println(data);
    }
}

@EnableAsync
@SpringBootApplication
public class CGLibProxyApplication {

    @Autowired MemberService memberService;

    public static void main(String[] args) {
        SpringApplication.run(CGLibProxyApplication.class);

    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            //결과가 lass skan.spring.cglib.MemberService$$EnhancerBySpringCGLIB$$2a6c5df2
            System.out.println(memberService.getClass());
        };
    }
}


