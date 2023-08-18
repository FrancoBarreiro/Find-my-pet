package com.findmypet.services.impl;

import com.findmypet.dtos.UserDto;
import com.findmypet.exceptions.BadRequestException;
import com.findmypet.mappers.UserMapper;
import com.findmypet.persistence.entities.User;
import com.findmypet.persistence.repositories.IUserRepository;
import com.findmypet.services.IUserService;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class UserService implements IUserService {

    private final Validator validator;

    @Autowired
    private IUserRepository userRepository;

    public UserService(Validator validator) {
        this.validator = validator;
    }

    @Override
    public UserDto signUp(UserDto user) {
        if (validator.validate(user).isEmpty()) {
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new DataIntegrityViolationException("Username already exists.");
            }
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new DataIntegrityViolationException("Email already exists.");
            }
            User userToSave = UserMapper.dtoToEntity(user);
            return UserMapper.entityToDto(userRepository.save(userToSave));
        } else {
            throw new BadRequestException("Check the fields");
        }
    }

}
