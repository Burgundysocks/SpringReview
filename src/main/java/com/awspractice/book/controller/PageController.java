package com.awspractice.book.controller;

import com.awspractice.book.config.auth.dto.SessionUser;
import com.awspractice.book.domain.dto.PostDTO;
import com.awspractice.book.service.post.PostService;
import com.awspractice.book.service.post.PostServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class PageController {

    private final PostService pservice;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        model.addAttribute("user", sessionUser);
        return "index";
    }
    @GetMapping("/user/public/info")
    public String userinfo(HttpSession session, Model model) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        model.addAttribute("user", sessionUser);
        return "user/userinfo";
    }

    @GetMapping("/board/public/list")
    public String list(HttpSession session, Model model) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        model.addAttribute("user", sessionUser);
        return "post/list";
    }
    @GetMapping("/board/user/write")
    public String write(HttpSession session, Model model) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        model.addAttribute("user", sessionUser);
        return "post/write";
    }

    @GetMapping("/board/public/get/{postId}")
    public String get(HttpSession session, @PathVariable Long postId, Model model) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        model.addAttribute("user", sessionUser);
        PostDTO post = pservice.getPostById(postId);
        model.addAttribute("post", post);
        return"post/get";
    }

}
