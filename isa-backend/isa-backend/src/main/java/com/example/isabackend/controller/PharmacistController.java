package com.example.isabackend.controller;

import com.example.isabackend.services.IPharmacistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
