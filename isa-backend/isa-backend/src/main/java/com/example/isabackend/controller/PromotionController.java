package com.example.isabackend.controller;

import com.example.isabackend.dto.request.*;
import com.example.isabackend.dto.response.PricelistResponse;
import com.example.isabackend.dto.response.PromotionResponse;
import com.example.isabackend.services.IPromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionController {
    private final IPromotionService _promotionService;

    public PromotionController(IPromotionService promotionService) {
        _promotionService = promotionService;
    }

    @PutMapping("/subscribe/{id}/patient")
    public void subscribePatient(@PathVariable("id")Long id, @RequestBody SubscribePatientRequest request){
        _promotionService.subscribePatient(id, request);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPromotion(@RequestBody CreatePromotionRequest request){
        PromotionResponse response = _promotionService.createPromotion(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Promotion cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/patient")
    public ResponseEntity<?> getAllByPatientId(@PathVariable("id") Long id){
        List<PromotionResponse> promotionResponses = _promotionService.getAllByPatientId(id);
        if(promotionResponses != null) {
            return new ResponseEntity<>(promotionResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Promotions doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/cancel-subscription/{id}/patient")
    public void cancelSubscription(@PathVariable("id")Long id, @RequestBody GetIdRequest request){
        _promotionService.cancelSubscription(id, request);
    }
}
