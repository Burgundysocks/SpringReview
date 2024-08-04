package com.awspractice.book.config.auth.dto;

import com.awspractice.book.domain.dto.UserDTO;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(UserDTO user) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    //인증된 사용자 정보만 필요합니다. 그외에 필요한건 없으니 name, email, picture만 필드 선언

}
