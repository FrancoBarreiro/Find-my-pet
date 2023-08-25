package com.findmypet.controllers;

import com.findmypet.dtos.UserDto;
import com.findmypet.security.JwtAuthenticationResponse;
import com.findmypet.services.IAuthenticationService;
import com.findmypet.services.IJwtService;
import com.findmypet.services.IUserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    @Autowired
    private IJwtService jwtService;

    @Autowired
    private IUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authenticationService.signup(userDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authenticationService.signin(userDto));
    }

    @PostMapping("/validate-token")
    @ResponseBody
    public ResponseEntity<?> validateToken(@RequestBody String token) {
        boolean isValid = false;
        try {
            String username = jwtService.extractUserName(token);
            UserDetails user = userService.userDetailsService().loadUserByUsername(username);

            isValid = jwtService.isTokenValid(token, user);
            return ResponseEntity.ok(isValid);
        } catch (ExpiredJwtException e) {
            return ResponseEntity.ok(isValid);
        }
    }
}
