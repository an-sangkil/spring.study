package skan.spring.dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
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


interface UserService {
    void save(String data);
}
@Service
class UserServiceImpl implements UserService {

    @Async
    @Override
    public void save(String data) {

    }
}

@EnableAsync
@SpringBootApplication
public class DynamicProxyApplication {

    @Autowired
    ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(DynamicProxyApplication.class);
    }
    @Bean
    public CommandLineRunner init(){
        return (strings)->{

            UserService userService = applicationContext.getBean(UserService.class);
            System.out.println(userService.getClass());
            // 결과가  lass skan.$Proxy33
        };
    }
}

