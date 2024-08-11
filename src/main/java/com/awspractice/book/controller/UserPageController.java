package com.awspractice.book.controller;

import com.awspractice.book.config.auth.dto.SessionUser;
import com.awspractice.book.domain.dto.UserDTO;
import com.awspractice.book.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class UserPageController {

    private final UserService service;

    @GetMapping("/user/public/info")
    public String userinfo(HttpSession session, Model model) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login"; // 로그인 세션이 없으면 리디렉션
        }

        UserDTO userinfo = service.getUserById(sessionUser.getId());
        model.addAttribute("user", sessionUser);
        model.addAttribute("userinfo", userinfo);

        System.out.println("Session ID: " + sessionUser.getId());
        System.out.println("Userinfo ID: " + userinfo.getId());

        return "user/userinfo";
    }

    @GetMapping("/user/personal/modify")
    public String usermodify(HttpSession session, Model model) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        model.addAttribute("user", sessionUser);
        return "user/usermodify";
    }


}
