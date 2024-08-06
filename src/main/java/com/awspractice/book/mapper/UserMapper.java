package com.awspractice.book.mapper;


import com.awspractice.book.domain.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    int save(UserDTO user);
    UserDTO findByEmail(String email);
    UserDTO findById(Long id);
    int update(UserDTO user);
    int delete(Long id);

    int update(String name, String picture);
}
