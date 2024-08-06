package com.awspractice.book.service.user;

import com.awspractice.book.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO join(UserDTO userDTO);

    UserDTO getUserByEmail(String email);

    UserDTO getUserById(Long id);

    UserDTO update(UserDTO userDTO);

    boolean deleteUser(Long id);

    UserDTO update2(String name, String picture);
}
