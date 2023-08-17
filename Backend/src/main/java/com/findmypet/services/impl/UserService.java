package com.findmypet.services.impl;

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
    public User signUp(User user) {
        return userRepository.save(user);
    }
}
