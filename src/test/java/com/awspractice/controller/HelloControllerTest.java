package com.awspractice.controller;

import com.awspractice.book.BookApplication;
import com.awspractice.web.controller.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@WebMvcTest(controllers = HelloController.class)
//웹에 집중할 수 있는 어노테이션
class HelloControllerTest {

    @Autowired
    //스프링이 관여하는 bean 주입
    private MockMvc mvc;
    //웹 api테스트시 사용
    //이를 통해 http get,post등에 대한 테스트 실행 가능


    // Configuration 클래스 정의
    @Configuration
    // Spring 컨텍스트가 이 클래스를 구성 클래스로 인식하도록 지정
    @ComponentScan(basePackages = "com.awspractice", excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = BookApplication.class)
    })
    // basePackages = "com.awspractice" 패키지에서 빈을 스캔
    // excludeFilters를 사용하여 AwsPracticeApplication 클래스를 스캔에서 제외
    // FilterType.ASSIGNABLE_TYPE은 특정 클래스 유형을 필터링하는데 사용

    static class Config {
        // Config 클래스는 빈 설정을 포함할 수 있으며, 여기서는 빈을 스캔하고 필터링하는 역할
    }


    @Test
    void hello() throws Exception {
        String hello = "Hello World!";

        mvc.perform(get("/hello"))
                //mockmvc를 통해 /hello 주소로 get요청
                //체이닝이되어 여러 검증을 이어서 선언 가능
                .andExpect(status().isOk())
                //mvc.perform의 결과 검증
                //HTTP 헤더의 status를 검증
                //isOk를 통해 200인지 아닌지 검증
                .andExpect(content().string(hello));
                //결과 점증
                //응답 본문 내용 검증
                //hello를 리턴사이게 그 값이 맞는가 검증
    }

    @Test
    public void helloData리턴 () throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                //param은 API를 테스트할 때 요청 파라미터를 등록합니다.
                .param("amount", String.valueOf(amount)))
                //단, 값은 String만 되기에 int인 amount를 String.valueOf로 문자열로 변환시킵니다.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                //jsonPath는 응답값을 필드별로 검증할 수 있는 메소드입니다.
                //$를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount").value(amount));
                //name과 amount를 증명하니 $.name,$.amount 사용
    }
}
