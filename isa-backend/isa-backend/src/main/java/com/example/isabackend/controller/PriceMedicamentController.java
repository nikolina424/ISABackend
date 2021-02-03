package com.example.isabackend.controller;

import com.example.isabackend.dto.request.PriceMedicamentRequest;
import com.example.isabackend.dto.request.ShiftRequest;
import com.example.isabackend.dto.response.PriceMedicamentResponse;
import com.example.isabackend.dto.response.PricelistResponse;
import com.example.isabackend.dto.response.ShiftResponse;
import com.example.isabackend.services.IPriceMedicamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price-medicaments")
public class PriceMedicamentController {
    private final IPriceMedicamentService _pricelistMedicamentService;

    public PriceMedicamentController(IPriceMedicamentService pricelistMedicamentService) {
        _pricelistMedicamentService = pricelistMedicamentService;
    }


    @GetMapping("/{id}/pharmacy")
    public ResponseEntity<?> getAllByPharmacyId(@PathVariable("id") Long id){
        List<PriceMedicamentResponse> priceMedicamentResponses = _pricelistMedicamentService.getAllByPharmacyId(id);
        if(priceMedicamentResponses != null) {
            return new ResponseEntity<>(priceMedicamentResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Price medicaments doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?>  createPriceMedicament(@RequestBody PriceMedicamentRequest request){
        PriceMedicamentResponse response = _pricelistMedicamentService.createPriceMedicament(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Price medicament cannot be created.", HttpStatus.NOT_FOUND);
        }
    }


}
