package com.awspractice.web.controller;

import com.awspractice.web.dto.HelloResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
// 공통되는 매핑에 대한 설정을 할 수 있습니다.
// 이 컨트롤러에 모든 매핑은 /hello라는 기본값을 갖게 됩니다.
public class HelloController {

    @GetMapping("")
    // 기존에는 /hello라는 매핑이 있었지만 공통매핑은 선언하고 비워놨으므로 /hello와 같은 역할을 하게 됩니다.
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/dto")
    public HelloResponseDTO helloDTO(@RequestParam ("name") String name,
                                     @RequestParam ("amount") int amount){
        return new HelloResponseDTO(name, amount);
        //RequestParam이란?
        //외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
        //외부에서 @RequestParam ("name")이란 이름으로 넘긴 파라미터를 String name여기에 저장하게 됩니다.
    }
}
