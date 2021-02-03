package com.example.isabackend.controller;

import com.example.isabackend.dto.request.CreatePricelistRequest;
import com.example.isabackend.dto.request.PriceMedicamentRequest;
import com.example.isabackend.dto.response.PriceMedicamentResponse;
import com.example.isabackend.dto.response.PricelistResponse;
import com.example.isabackend.services.IPricelistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pricelists")
public class PricelistController {

    private final IPricelistService _pricelistService;

    public PricelistController(IPricelistService pricelistService) {
        _pricelistService = pricelistService;
    }

    @GetMapping("/{id}/pharmacy")
    public ResponseEntity<?> getAllByPharmacyId(@PathVariable("id") Long id){
        List<PricelistResponse> pricelistResponses = _pricelistService.getAllByPharmacyId(id);
        if(pricelistResponses != null) {
            return new ResponseEntity<>(pricelistResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Pricelists doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/active/{id}/pharmacy")
    public ResponseEntity<?> getActivePricelistByPharmacyId(@PathVariable("id") Long id){
        PricelistResponse pricelistResponse = _pricelistService.getActivePricelistByPharmacyId(id);
        if(pricelistResponse != null) {
            return new ResponseEntity<>(pricelistResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Pricelist doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?>  createPricelist(@RequestBody CreatePricelistRequest request){
        PricelistResponse response = _pricelistService.createPricelist(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Price medicament cannot be created.", HttpStatus.NOT_FOUND);
        }
    }


}
