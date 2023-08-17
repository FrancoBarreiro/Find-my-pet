package com.findmypet.services;

import com.findmypet.dtos.UserDto;

public interface IUserService {

    UserDto signUp(UserDto user);
}
