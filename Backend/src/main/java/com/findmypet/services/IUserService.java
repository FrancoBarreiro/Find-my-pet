package com.findmypet.services;

import com.findmypet.dtos.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService {

    UserDetailsService userDetailsService();

    UserDto signUp(UserDto user);
}
