package com.findmypet.mappers;

import com.findmypet.dtos.UserDto;
import com.findmypet.persistence.entities.User;

public class UserMapper {

    public static User dtoToEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .cellphone(userDto.getPhoneNumber())
                .build();
    }

    public static UserDto entityToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phoneNumber(user.getCellphone())
                .build();
    }
}
