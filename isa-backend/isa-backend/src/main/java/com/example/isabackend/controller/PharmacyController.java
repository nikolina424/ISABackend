package com.example.isabackend.controller;

import com.example.isabackend.dto.request.PharmacyAdminRequest;
import com.example.isabackend.dto.request.PharmacyRequest;
import com.example.isabackend.dto.request.UpdatePatientRequest;
import com.example.isabackend.dto.request.UpdatePharmacyRequest;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.dto.response.PharmacyResponse;
import com.example.isabackend.dto.response.TempResponse;
import com.example.isabackend.services.IPharmacyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {
    private final IPharmacyService _pharmacyService;

    public PharmacyController(IPharmacyService pharmacyService) {
        _pharmacyService = pharmacyService;
    }

    @PutMapping("/{id}")
    public void updatePharmacy(@PathVariable("id")Long id, @RequestBody UpdatePharmacyRequest request){
        _pharmacyService.updatePharmacy(id, request);
    }

    @GetMapping()
    public List<PharmacyResponse> getAllPharmacies(){
        return _pharmacyService.getAllPharmacies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPharmacy(@PathVariable("id") Long id){
        PharmacyResponse pharmacyResponse = _pharmacyService.getPharmacyById(id);
        if(pharmacyResponse != null) {
            return new ResponseEntity<>(pharmacyResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register-pharmacy")
    public ResponseEntity<?> registerPharmacy(@RequestBody PharmacyRequest request){
        TempResponse temp = new TempResponse();
        temp.setText("Registered pharmacy");
        if(_pharmacyService.registerPharmacy(request)){
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Pharmacy already exists !", HttpStatus.NOT_FOUND);
        }
    }
}
