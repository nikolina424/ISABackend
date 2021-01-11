package com.example.isabackend.controller;


import com.example.isabackend.dto.request.LoginRequest;
import com.example.isabackend.dto.request.RegistrationRequest;
import com.example.isabackend.dto.response.UserResponse;
import com.example.isabackend.security.TokenUtils;
import com.example.isabackend.services.IAuthService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenUtils _tokenUtils;

    private final IAuthService _authService;

    public AuthController(TokenUtils tokenUtils, IAuthService authService) {
        _tokenUtils = tokenUtils;
        _authService = authService;
    }

    @GetMapping("/verify")
    public String verify(@RequestHeader("Auth-Token") String token) throws NotFoundException {
        return _tokenUtils.getUsernameFromToken(token);
    }

    @GetMapping("/permission")
    public String getPermissions(@RequestHeader("Auth-Token") String token) throws NotFoundException {
        return _authService.getPermission(token);
    }


    @PutMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request){
        return _authService.login(request);
    }

    @PostMapping("/register-patient")
    public UserResponse registerPatient(@RequestBody RegistrationRequest request){
        return _authService.registerPatient(request);
    }


}