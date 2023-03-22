package skan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import skan.aop.MemberService;

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
@Slf4j
@SpringBootApplication
public class SrpingApplication {

    @Autowired(required = false)
    MemberService memberService;

    public static void main(String[] args) {
        SpringApplication.run(SrpingApplication.class);
    }

    @Bean
    public CommandLineRunner init(){

        return (strings)->{
            log.info("init str = {}",strings);
            // memberService.save("dada");
        };
    }
}
