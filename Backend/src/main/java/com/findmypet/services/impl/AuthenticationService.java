package com.findmypet.services.impl;

import com.findmypet.dtos.UserDto;
import com.findmypet.mappers.UserMapper;
import com.findmypet.persistence.entities.User;
import com.findmypet.persistence.repositories.IUserRepository;
import com.findmypet.security.JwtAuthenticationResponse;
import com.findmypet.services.IAuthenticationService;
import com.findmypet.services.IJwtService;
import com.findmypet.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(UserDto userDto) {
        User userToSave = UserMapper.dtoToEntity(userDto);
        userToSave.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userToSave.setRole(Role.USER);
        userRepository.save(userToSave);
        var jwt = jwtService.generateToken(userToSave);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(UserDto userDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        User user = userRepository.findByEmailOrUsername(userDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
