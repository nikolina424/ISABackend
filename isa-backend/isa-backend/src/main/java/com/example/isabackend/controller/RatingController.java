package com.example.isabackend.controller;

import com.example.isabackend.dto.request.RateRequest;
import com.example.isabackend.dto.request.ShiftRequest;
import com.example.isabackend.dto.response.RatingResponse;
import com.example.isabackend.dto.response.ShiftResponse;
import com.example.isabackend.services.IRatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final IRatingService _ratingService;

    public RatingController(IRatingService ratingService) {
        _ratingService = ratingService;
    }

    @PostMapping("/pharmacy")
    public ResponseEntity<?> ratePharmacy(@RequestBody RateRequest rateRequest){
        RatingResponse response = _ratingService.ratePharmacy(rateRequest);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Rating cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/medicament")
    public ResponseEntity<?>  rateMedicament(@RequestBody RateRequest rateRequest){
        RatingResponse response = _ratingService.rateMedicament(rateRequest);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Rating cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/pharmacist")
    public ResponseEntity<?>  ratePharmacist(@RequestBody RateRequest rateRequest){
        RatingResponse response = _ratingService.ratePharmacist(rateRequest);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Rating cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dermatologist")
    public ResponseEntity<?>  rateDermatologist(@RequestBody RateRequest rateRequest){
        RatingResponse response = _ratingService.rateDermatologist(rateRequest);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Rating cannot be created.", HttpStatus.NOT_FOUND);
        }
    }
}
