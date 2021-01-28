package com.example.isabackend.controller;

import com.example.isabackend.dto.request.PharmacistRequest;
import com.example.isabackend.dto.request.ShiftRequest;
import com.example.isabackend.dto.response.DermatologistResponse;
import com.example.isabackend.dto.response.PharmacistResponse;
import com.example.isabackend.dto.response.ShiftResponse;
import com.example.isabackend.services.IPharmacistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacists")
public class PharmacistController {

    private final IPharmacistService _pharmacistService;

    public PharmacistController(IPharmacistService pharmacistService) {
        _pharmacistService = pharmacistService;
    }


    @GetMapping("/search")
    public ResponseEntity<?> searchPharmacist(@RequestParam("firstName") String firstName, @RequestParam(value="lastName") String lastName){
        return new ResponseEntity<>(_pharmacistService.searchPharmacist(firstName, lastName), HttpStatus.OK);
    }

    @GetMapping("/{id}/pharmacy")
    public ResponseEntity<?> getAllPharmacistByPharmacyId(@PathVariable("id") Long id){
        List<PharmacistResponse> pharmacistResponses = _pharmacistService.getAllPharmacistByPharmacyId(id);
        if(pharmacistResponses != null) {
            return new ResponseEntity<>(pharmacistResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Pharmacists in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
}
