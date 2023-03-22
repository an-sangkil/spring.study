package skan.aop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <pre>
 * Description :
 *
 *
 * </pre>
 *
 * @author skan
 * @version Copyright (C) 2022 by CJENM|MezzoMedia. All right reserved.
 * @since 2022/03/24
 */
@SpringBootTest
class MemberServiceImplTest {


    @Autowired MemberService memberService;

    @Test
    @DisplayName("저장 AOP")
    public void save() throws Exception {
        memberService.save("memeber name");
    }


    @Test
    @DisplayName("예외를 일으켜 AOP 확인하기 ")
    public void aopException() throws Exception {

        memberService.delete("");

    }
}