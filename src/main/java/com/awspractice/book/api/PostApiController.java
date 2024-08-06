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
        PostDTO result = service.regist(postDTO);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }


    @GetMapping("/public/list")
    public ResponseEntity<List<PostDTO>> list() {
        List<PostDTO> posts = service.getPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/public/{postId}")
    public ResponseEntity<PostDTO> get(@PathVariable Long postId) {
        PostDTO post = service.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @PutMapping(value="/user/{postId}", consumes = "application/json")
    public ResponseEntity<PostDTO> modify(@RequestBody PostDTO postDTO) {
        return service.updatePost(postDTO)?
            new ResponseEntity<PostDTO>(HttpStatus.OK) :
            new ResponseEntity<PostDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value="/user/{postId}")
    public ResponseEntity<String> remove(@PathVariable("postId") long postId){
        return service.deletePostById(postId) ?
                new ResponseEntity<String>(HttpStatus.OK) :
                new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
