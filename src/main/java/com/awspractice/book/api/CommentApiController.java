package com.awspractice.book.api;

import com.awspractice.book.domain.dto.CommentDTO;
import com.awspractice.book.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentApiController {

    private final CommentService cService;

    @PostMapping(value = "/public/comment/insert", consumes = "application/json", produces = "application/json;charset=utf-8")
    public ResponseEntity<CommentDTO> insertComment(@RequestBody CommentDTO cdto) {
        CommentDTO comment = cService.addComment(cdto);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping(value = "/public/comment/get/{postId}")
    public ResponseEntity<List<CommentDTO>> getComment(@RequestParam Long postId) {
        List<CommentDTO> comments = cService.getComments(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PutMapping(value = "/user/comment/{id}", consumes = "application/json", produces = "application/json;charset=utf-8")
    public ResponseEntity<Map<String, String>> updateComment(@PathVariable Long id, @RequestBody CommentDTO cdto) {
        Map<String, String> response = new HashMap<>();
        boolean isUpdate = cService.updateComment(id, cdto);
        if (isUpdate) {
            response.put("comment", "댓글 업데이트 성공");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("comment", "댓글 업데이트 실패");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/comment/{id}")
    public ResponseEntity<Map<String, String>> deleteComment(@PathVariable Long id) {
       Map<String, String> response = new HashMap<>();
       boolean isDelete = cService.deleteComment(id);
       if (isDelete) {
           response.put("comment", "삭제 성공");
           return new ResponseEntity<>(response, HttpStatus.OK);
       } else{
           response.put("comment", "삭제 실패");
           return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}
