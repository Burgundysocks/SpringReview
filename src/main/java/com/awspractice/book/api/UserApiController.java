package com.awspractice.book.api;


import com.awspractice.book.domain.dto.UserDTO;
import com.awspractice.book.service.user.UserService;
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
public class UserApiController {

    private final UserService service;

    @GetMapping("/public/user/userinfo/{id}")
    public ResponseEntity<UserDTO> userInfo(@PathVariable Long id) {
        UserDTO user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }


    @PutMapping("/user/usermodi/{userId}")
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDTO user) {
        user.setId(userId);

        Map<String, String> response = new HashMap<>();
        boolean isUpdated = service.update(userId, user);
        if (isUpdated) {
            response.put("message", "정보가 성공적으로 수정되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "수정 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
