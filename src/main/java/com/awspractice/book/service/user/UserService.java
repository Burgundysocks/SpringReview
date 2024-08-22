package com.awspractice.book.service.user;

import com.awspractice.book.domain.dto.UserDTO;

public interface UserService {

    UserDTO saveUser(UserDTO userDTO);

    UserDTO join(UserDTO userDTO);

    UserDTO getUserByEmail(String email);

    UserDTO getUserById(Long id);

    boolean update(Long id, UserDTO userDTO);

    boolean deleteUser(Long id);

    UserDTO update2(String name, String picture);
}
