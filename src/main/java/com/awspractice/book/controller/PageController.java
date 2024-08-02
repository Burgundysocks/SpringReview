package com.awspractice.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/list")
    public String list() {
        return "post/list"; // Thymeleaf 템플릿 이름을 반환
    }
}
