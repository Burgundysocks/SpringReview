package com.awspractice.book.controller;

import com.awspractice.book.config.auth.dto.SessionUser;
import com.awspractice.book.service.post.PostServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class PageController {

    private final PostServiceImpl postServiceImpl;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        Object user = session.getAttribute("user");
        model.addAttribute("user", user);
        return "index";
    }
    @GetMapping("/user/public/info")
    public String userinfo(HttpSession session, Model model) {
        Object user = session.getAttribute("user");
        model.addAttribute("user", user);
        return "user/userinfo";
    }

    @GetMapping("/board/public/list")
    public String list(HttpSession session, Model model) {
        Object user = session.getAttribute("user");
        model.addAttribute("user", user);
        return "post/list";
    }
    @GetMapping("/board/user/write")
    public String write(HttpSession session, Model model) {
        Object user = session.getAttribute("user");
        model.addAttribute("user", user);
        return "post/write";
    }

}
