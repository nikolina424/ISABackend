package com.example.isabackend.controller;

import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.dto.response.PharmacyAdminResponse;
import com.example.isabackend.services.IPharmacyAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pharmacy-admins")
public class PharmacyAdminController {
    private final IPharmacyAdminService _pharmacyAdminService;

    public PharmacyAdminController(IPharmacyAdminService pharmacyAdminService) {
        _pharmacyAdminService = pharmacyAdminService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPharmacyAdmin(@PathVariable("id") Long id){
        PharmacyAdminResponse pharmacyAdminResponse = _pharmacyAdminService.getPharmacyAdminById(id);
        if(pharmacyAdminResponse != null) {
            return new ResponseEntity<>(pharmacyAdminResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Pharmacy admin doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getPharmacyAdminByUserId(@PathVariable("id") Long id){
        PharmacyAdminResponse pharmacyAdminResponse = _pharmacyAdminService.getPharmacyAdminByUserId(id);
        if(pharmacyAdminResponse != null) {
            return new ResponseEntity<>(pharmacyAdminResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Pharmacy admin doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
}
