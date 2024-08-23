package com.awspractice.book.api;



import com.awspractice.book.config.auth.dto.SessionUser;
import com.awspractice.book.domain.dto.UserDTO;
import com.awspractice.book.service.user.UserService;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class UserApiController {

    private final UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value="/public/join", consumes = "application/json", produces = "application/json;charset=utf-8")
    public ResponseEntity<Map<String, Object>>  createUser(@RequestBody UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();
        try{
            UserDTO result = service.saveUser(userDTO);
            if (result == null) {
                response.put("message", "저장 중 오류가 발생했습니다.");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                response.put("message", "성공적으로 저장되었습니다.");
                response.put("user", result);
                return new ResponseEntity<>(response, OK);
            }
        } catch (Exception e){
            e.printStackTrace();
            response.put("message", "서버 오류가 발생했습니다.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/public/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDTO request, HttpServletRequest httpRequest) {
        String email = request.getEmail();
        String passwordLock = request.getPassword();

        log.info("Login attempt for email: " + email);

        UserDTO user = service.getUserByEmail(email);
        Map<String, String> response = new HashMap<>();

        if (user == null) {
            log.error("Login failed: User not found for email " + email);
            response.put("status", "error");
            response.put("message", "User not found");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        if (passwordEncoder.matches(passwordLock, user.getPassword())) {
            HttpSession session = httpRequest.getSession(true);
            SessionUser sessionUser = new SessionUser(user); // Create SessionUser
            session.setAttribute("user", sessionUser); // Store in session
            log.info("Login successful for email: " + email);
            response.put("status", "success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            log.error("Login failed: Incorrect password for email " + email);
            response.put("status", "error");
            response.put("message", "Incorrect password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }




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
            return new ResponseEntity<>(response, OK);
        } else {
            response.put("message", "수정 중 오류가 발생했습니다.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
