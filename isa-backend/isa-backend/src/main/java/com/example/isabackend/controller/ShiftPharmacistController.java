package com.example.isabackend.controller;

import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.services.IShiftPharmacistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shifts-pharmacists")
public class ShiftPharmacistController {
    private final IShiftPharmacistService _shiftPharmacistService;

    public ShiftPharmacistController(IShiftPharmacistService shiftPharmacistService) {
        _shiftPharmacistService = shiftPharmacistService;
    }


}
