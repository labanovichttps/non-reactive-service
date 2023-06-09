package com.labanovich.user.service.impl;

import com.labanovich.user.dto.UserCreateDto;
import com.labanovich.user.dto.UserDto;
import com.labanovich.user.entity.User;
import com.labanovich.user.exception.EntityNotFoundException;
import com.labanovich.user.mapper.UserMapper;
import com.labanovich.user.repository.UserRepository;
import com.labanovich.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id)
            .map(userMapper::toUserDto)
            .orElseThrow(() -> new EntityNotFoundException("id", User.class, id));
    }

    @Override
    @Transactional
    public UserDto save(UserCreateDto createDto) {
        User user = userMapper.toUser(createDto);
        User savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }
}
