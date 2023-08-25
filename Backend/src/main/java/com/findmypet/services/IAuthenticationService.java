package com.findmypet.services;

import com.findmypet.dtos.UserDto;
import com.findmypet.security.JwtAuthenticationResponse;

public interface IAuthenticationService {
    JwtAuthenticationResponse signup(UserDto userDto);

    JwtAuthenticationResponse signin(UserDto userDto);
}
