package com.awspractice.book.config.auth.dto;

import com.awspractice.book.domain.dto.Role;
import com.awspractice.book.domain.dto.UserDTO;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String nickname;
    private String picture;
    private Role role;

    public SessionUser(UserDTO user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.picture = user.getPicture();
        this.role = user.getRole();
    }

    //인증된 사용자 정보만 필요합니다. 그외에 필요한건 없으니 name, email, picture만 필드 선언

}
