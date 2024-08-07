package com.awspractice.book.api;

import com.awspractice.book.domain.dto.PostDTO;
import com.awspractice.book.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping(value="/user/posts/{postId}", consumes = "application/json")
    public ResponseEntity<PostDTO> modify(@RequestBody PostDTO postDTO) {
        return service.updatePost(postDTO)?
            new ResponseEntity<PostDTO>(HttpStatus.OK) :
            new ResponseEntity<PostDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value="/user/posts/{postId}")
    public ResponseEntity<String> remove(@PathVariable("postId") long postId){
        return service.deletePostById(postId) ?
                new ResponseEntity<String>(HttpStatus.OK) :
                new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
