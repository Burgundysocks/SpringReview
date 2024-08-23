package com.awspractice.book.controller;

import com.awspractice.book.config.auth.dto.SessionUser;
import com.awspractice.book.domain.dto.PostDTO;
import com.awspractice.book.service.post.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@Controller
@Slf4j
public class PostPageController {

    private final PostService pservice;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        model.addAttribute("user", sessionUser);
        return "index";
    }

    @GetMapping("/board/public/list")
    public String list(HttpSession session, Model model) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        log.info("Logged in user: " + sessionUser.getEmail() + ", Roles: " + sessionUser.getRole());
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
        log.info("Logged in user: " + sessionUser.getEmail() + ", Roles: " + sessionUser.getRole());
        PostDTO post = pservice.getPostById(postId);
        model.addAttribute("post", post);
        return"post/get";
    }

    @GetMapping("/board/public/recent/{userId}")
    public RedirectView getUserLast(HttpSession session, @PathVariable Long userId, Model model) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        model.addAttribute("user", sessionUser);
        PostDTO post = pservice.getLastPost(userId);

        if (post != null) {
            Long postId = post.getPostId();
            return new RedirectView("/board/public/get/" + postId);
        } else {
            // 게시글이 없을 경우, 다른 페이지로 리디렉션하거나 에러 페이지를 보여줄 수 있습니다.
            return new RedirectView("/board/public/list"); // 예: 게시글 목록 페이지로 리디렉션
        }
    }
    @GetMapping("/board/user/modify/{postId}")
    public String modify(HttpSession session, @PathVariable Long postId, Model model) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        model.addAttribute("user", sessionUser);
        PostDTO post = pservice.getPostById(postId);
        model.addAttribute("post", post);
        return"post/modify";
    }

}
