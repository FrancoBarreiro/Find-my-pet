package com.findmypet.services;

import com.findmypet.persistence.entities.User;

public interface IUserService {

    User signUp(User user);
}
