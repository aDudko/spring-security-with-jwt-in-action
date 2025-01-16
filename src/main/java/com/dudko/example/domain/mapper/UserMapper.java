package com.dudko.example.domain.mapper;

import com.dudko.example.domain.entity.User;
import com.dudko.example.model.dto.ProfileDto;
import com.dudko.example.model.dto.RegisterDto;

public class UserMapper {

    public static ProfileDto mapToProfileDto(User user) {
        return ProfileDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .passwordHash(user.getPassword())
                .build();
    }

    public static User mapRegisterDtoToUser(RegisterDto registerDto) {
        return User.builder()
                .name(registerDto.getName())
                .email(registerDto.getEmail())
                .build();
    }

}
