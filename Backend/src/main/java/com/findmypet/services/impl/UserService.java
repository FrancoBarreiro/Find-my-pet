package com.findmypet.services.impl;

import com.findmypet.dtos.UserDto;
import com.findmypet.mappers.UserMapper;
import com.findmypet.persistence.entities.User;
import com.findmypet.persistence.repositories.IUserRepository;
import com.findmypet.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDto signUp(UserDto user) {

        User userToSave = UserMapper.dtoToEntity(user);
        return UserMapper.entityToDto(userRepository.save(userToSave));

    }
}
