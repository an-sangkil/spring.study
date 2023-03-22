package skan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import skan.aop.AopConfig;
import skan.aop.MemberService;
import skan.aop.MemberServiceImpl;

/**
 *
 * @author skan
 * @version Copyright (C) 2022 by CJENM|MezzoMedia. All right reserved.
 * @since 2022/03/23
 */
public class Application {

    /**
     * Spring 의 모든 모듈을 Ioc 에 올리지 않고 필요한 부분만 올려서 사용하는 예제.
     */
//    public static void main(String[] args) {
//
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
//        MemberService memberService = applicationContext.getBean("memberServiceImpl",MemberServiceImpl.class);
//        memberService.save("test data");
//
//    }
}
