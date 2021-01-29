package com.example.isabackend.services;


import com.example.isabackend.controller.SupplierRequest;
import com.example.isabackend.dto.request.*;
import com.example.isabackend.dto.response.UserResponse;

public interface IAuthService {

    String getPermission(String token);

    UserResponse login(LoginRequest request);

    UserResponse registerPatient(RegistrationRequest request);


    boolean registerPharmacist(PharmacistRequest request);

    void changePasswordForPharmacyAdmin(Long id, ChangePasswordRequest request);

    boolean registerPharmacyAdmin(PharmacyAdminRequest request);

    boolean registerSystemAdmin(SystemAdminRequest request);

    boolean registerDermatologist(DermatologistRequest request);

    boolean registerSupplier(SupplierRequest request);
}
