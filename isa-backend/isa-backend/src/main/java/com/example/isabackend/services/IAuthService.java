package com.example.isabackend.services;


import com.example.isabackend.dto.request.LoginRequest;
import com.example.isabackend.dto.request.PharmacistRequest;
import com.example.isabackend.dto.request.RegistrationRequest;
import com.example.isabackend.dto.response.UserResponse;

public interface IAuthService {

    String getPermission(String token);

    UserResponse login(LoginRequest request);

    UserResponse registerPatient(RegistrationRequest request);


    boolean registerPharmacist(PharmacistRequest request);
}
