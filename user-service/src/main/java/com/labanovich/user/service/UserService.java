package com.labanovich.user.service;

import com.labanovich.user.dto.UserCreateDto;
import com.labanovich.user.dto.UserDto;

public interface UserService {

    UserDto findById(Long id);

    UserDto save(UserCreateDto createDto);
}
