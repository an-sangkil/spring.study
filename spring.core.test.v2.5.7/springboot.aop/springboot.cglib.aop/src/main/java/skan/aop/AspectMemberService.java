package skan.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import skan.annotation.Retry;

import java.util.Arrays;
import java.util.stream.Collectors;

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
@Aspect
@Component
public class AspectMemberService {

    @Before("@annotation(skan.annotation.Retry)")
    public void doSomethingBefore(JoinPoint joinPoint) {
        log.info("------------------------ BEFORE START ");
        log.info("before joinPoint= {}", joinPoint.getKind());
        Arrays.stream(joinPoint.getArgs()).
                map(o -> "+plus")
                .collect(Collectors.toList());
                ;
        log.info("------------------------ BEFORE END ");

    }


    @Around(value ="execution(* skan.aop.MemberService.save(String))" +"@annotation(skan.annotation.Retry)")
    public Object processed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        log.info("------------------------ AOP-PLAIN  : START ");
        long begin = System.currentTimeMillis();

        log.info("args            = {}",proceedingJoinPoint.getArgs());
        log.info("signature       = {}",proceedingJoinPoint.getSignature());
        log.info("target          = {}",proceedingJoinPoint.getTarget());
        log.info("static part     = {}",proceedingJoinPoint.getStaticPart());
        log.info("kind            = {}",proceedingJoinPoint.getKind());
        log.info("source location = {}",proceedingJoinPoint.getSourceLocation());
        log.info("this            = {}",proceedingJoinPoint.getThis());
        log.info("class           = {}",proceedingJoinPoint.getClass());


        Object retVal = proceedingJoinPoint.proceed(); // 메서드 호출 자체를 감쌈
        log.info("aspect 실행 시간 = {} ", System.currentTimeMillis() - begin);

        log.info("------------------------ AOP-PLAIN  : END ");

        log.info("------------------------ AOP-ANNOTATION VALUE  : START");
        MethodSignature methodSignature =  (MethodSignature) proceedingJoinPoint.getSignature();
        Retry retry = methodSignature.getMethod().getAnnotation(Retry.class);
        log.info("retry count  = {} ", retry.value());
        log.info("------------------------ AOP-ANNOTATION VALUE : END");


        return retVal;
    }

    @AfterReturning(value = "@annotation(skan.annotation.Retry)", returning = "returnObject")
    public void doSomethingAfter(JoinPoint joinPoint, Object returnObject) {
        log.info("------------------------ AFTER  : START ");
        log.info("after returning joinPoint     = {}", joinPoint.getKind());
        log.info("after returning object = {}", returnObject);
        log.info("------------------------ AFTER : END");
    }

    @AfterThrowing(value = "@annotation(skan.annotation.Retry)", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint , Exception exception) {

        log.error("aop throwing",exception);

    }
}
