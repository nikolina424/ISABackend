package com.example.isabackend.controller;


import com.example.isabackend.dto.request.*;
import com.example.isabackend.dto.response.TempResponse;
import com.example.isabackend.dto.response.UserResponse;
import com.example.isabackend.security.TokenUtils;
import com.example.isabackend.services.IAuthService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register-pharmacist")
    public ResponseEntity<?> registerPharmacist(@RequestBody PharmacistRequest request){
        TempResponse temp = new TempResponse();
        temp.setText("Registered pharmacist");
        if(_authService.registerPharmacist(request)){
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User already exists !", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register-system-admin")
    public ResponseEntity<?> registerSystemAdmin(@RequestBody SystemAdminRequest request){
        TempResponse temp = new TempResponse();
        temp.setText("Registered system admin");
        if(_authService.registerSystemAdmin(request)){
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User already exists !", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register-dermatologist")
    public ResponseEntity<?> registerDermatologist(@RequestBody DermatologistRequest request){
        TempResponse temp = new TempResponse();
        temp.setText("Registered dermatologist");
        if(_authService.registerDermatologist(request)){
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User already exists !", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register-supplier")
    public ResponseEntity<?> registerSupplier(@RequestBody SupplierRequest request){
        TempResponse temp = new TempResponse();
        temp.setText("Registered supplier");
        if(_authService.registerSupplier(request)){
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User already exists !", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register-pharmacy-admin")
    public ResponseEntity<?> registerPharmacyAdmin(@RequestBody PharmacyAdminRequest request){
        TempResponse temp = new TempResponse();
        temp.setText("Registered pharmacy admin");
        if(_authService.registerPharmacyAdmin(request)){
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("User already exists !", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void changePasswordForPharmacyAdmin(@PathVariable("id")Long id, @RequestBody ChangePasswordRequest request){
        _authService.changePasswordForPharmacyAdmin(id, request);

    }



}