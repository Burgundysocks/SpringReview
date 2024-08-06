package com.awspractice.book.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String nickname;
    private String password;
    private String picture;
    private Role role;

    @Builder
    public UserDTO(String name, String email,Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public UserDTO update(String name) {
        this.name = name;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
