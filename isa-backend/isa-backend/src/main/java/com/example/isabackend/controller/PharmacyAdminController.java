package com.example.isabackend.controller;

import com.example.isabackend.dto.request.UpdatePatientRequest;
import com.example.isabackend.dto.request.UpdatePharmacyAdminRequest;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.dto.response.PharmacyAdminResponse;
import com.example.isabackend.services.IPharmacyAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public void updatePharmacyAdmin(@PathVariable("id")Long id, @RequestBody UpdatePharmacyAdminRequest request){
        _pharmacyAdminService.updatePharmacyAdmin(id, request);

    }
}
