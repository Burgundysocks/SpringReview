package com.awspractice.book.service.user;

import com.awspractice.book.domain.dto.UserDTO;
import com.awspractice.book.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        // 비밀번호를 암호화합니다.
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);

        // 암호화된 비밀번호를 데이터베이스에 저장합니다.
        userMapper.save(userDTO);
        return userDTO;
    }

    @Override
    public UserDTO join(UserDTO userDTO) {
        userMapper.save(userDTO);
        return userDTO;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public boolean update(Long id, UserDTO userDTO) {
        if (userMapper.update(userDTO)==1){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        return userMapper.delete(id) > 0;
    }

    @Override
    public UserDTO update2(String name, String picture) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        userDTO.setPicture(picture);
        userMapper.update(userDTO);
        return userDTO;
    }
}
