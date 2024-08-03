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


}
