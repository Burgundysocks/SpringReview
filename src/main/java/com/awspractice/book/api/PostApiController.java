package com.awspractice.book.api;

import com.awspractice.book.domain.dto.PostDTO;
import com.awspractice.book.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<PostDTO> save(@RequestBody PostDTO postDTO) {
        try {
            System.out.println("Received PostDTO: " + postDTO); // 데이터 확인용 로그
            if (postDTO.getPostTitle() == null || postDTO.getPostTitle().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 제목이 없으면 400 오류 반환
            }
            PostDTO result = service.regist(postDTO);
            if (result == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace(); // 콘솔에 예외 출력
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 오류 반환
        }
    }


    @GetMapping("/public/posts/list")
    public ResponseEntity<List<PostDTO>> list() {
        List<PostDTO> posts = service.getPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/public/posts/{postId}")
    public ResponseEntity<PostDTO> get(@PathVariable Long postId) {
        PostDTO post = service.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/public/userPosts/{userId}")
    public ResponseEntity<List<PostDTO>> getByUserId(@PathVariable Long userId) {
        List<PostDTO> posts = service.getPostsByUserId(userId);
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
            response.put("message", "게시글 저장 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @DeleteMapping(value="/user/posts/{postId}")
    public ResponseEntity<String> remove(@PathVariable("postId") long postId){
        return service.deletePostById(postId) ?
                new ResponseEntity<String>(HttpStatus.OK) :
                new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
