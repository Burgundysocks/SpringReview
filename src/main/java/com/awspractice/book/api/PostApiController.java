package com.awspractice.book.api;

import com.awspractice.book.domain.dto.PageDTO;
import com.awspractice.book.domain.dto.PostDTO;
import com.awspractice.book.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostApiController {

    private final PostService service;

    @PostMapping(value = "/user/posts", consumes = "application/json", produces = "application/json;charset=utf-8")
    public ResponseEntity<Map<String, Object>> save(@RequestBody PostDTO postDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (postDTO.getPostTitle() == null || postDTO.getPostTitle().isEmpty()) {
                response.put("message", "제목은 필수입니다.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            PostDTO result = service.regist(postDTO);
            if (result == null) {
                response.put("message", "게시글 저장 중 오류가 발생했습니다.");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                response.put("message", "게시글이 성공적으로 저장되었습니다.");
                response.put("post", result);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace(); // 콘솔에 예외 출력
            response.put("message", "서버 오류가 발생했습니다.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/public/posts/list")
    public ResponseEntity<Map<String, Object>> list(PageDTO page) {
        List<PostDTO> posts = service.getPosts(page);
        long totalPosts = service.getTotal();
        int totalPages = (int) Math.ceil((double) totalPosts / page.getPageSize());

        Map<String, Object> response = new HashMap<>();
        response.put("page", page.getPage());
        response.put("pageSize", page.getPageSize());
        response.put("totalPages", totalPages); // 총 페이지 수
        response.put("posts", posts);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/public/posts/{postId}")
    public ResponseEntity<PostDTO> get(@PathVariable Long postId) {
        PostDTO post = service.getPostById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(post);
    }

    @GetMapping("/public/userPosts/{userId}")
    public ResponseEntity<List<PostDTO>> getByUserId(@PathVariable Long userId) {
        List<PostDTO> posts = service.getPostsByUserId(userId);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(posts);
    }

    @PutMapping(value = "/user/posts/{postId}", consumes = "application/json")
    public ResponseEntity<Map<String, String>> modify(@PathVariable("postId") Long postId, @RequestBody PostDTO postDTO) {
        Map<String, String> response = new HashMap<>();
        boolean isUpdated = service.updatePost(postId, postDTO);
        if (isUpdated) {
            response.put("message", "게시글이 성공적으로 수정되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "게시글 수정 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping(value="/user/posts/{postId}")
    public ResponseEntity<Map<String, String>> remove(@PathVariable("postId") long postId){
        Map<String, String> response = new HashMap<>();
        boolean isDeleted = service.deletePostById(postId);
        if (isDeleted) {
            response.put("message", "게시글이 성공적으로 삭제되었습니다.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "게시글 삭제 중 오류가 발생했습니다.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
