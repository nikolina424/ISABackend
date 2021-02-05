package com.example.isabackend.controller;

import com.example.isabackend.dto.request.ChangeOfferRequest;
import com.example.isabackend.dto.request.CreateOfferRequest;
import com.example.isabackend.dto.request.CreateOrderMedicamentRequest;
import com.example.isabackend.dto.request.SubscribePatientRequest;
import com.example.isabackend.dto.response.OfferResponse;
import com.example.isabackend.dto.response.OrderMedicamentResponse;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.entity.Offer;
import com.example.isabackend.services.IOfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private final IOfferService _offerService;

    public OfferController(IOfferService offerService) {
        _offerService = offerService;
    }

    @GetMapping("/{id}/supplier")
    public ResponseEntity<?> getAllOffersBySupplierId(@PathVariable("id") Long id){
        List<OfferResponse> offerResponses = _offerService.getAllOffersBySupplierId(id);
        if(offerResponses != null) {
            return new ResponseEntity<>(offerResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Offers for this supplier doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOffer(@PathVariable("id") Long id){
        OfferResponse response = _offerService.getOffer(id);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Offer doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/purchase-order")
    public ResponseEntity<?> getAllOffersByPurchaseOrderId(@PathVariable("id") Long id){
        List<OfferResponse> offerResponses = _offerService.getAllOffersByPurchaseOrderId(id);
        if(offerResponses != null) {
            return new ResponseEntity<>(offerResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Offers for this order doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?> createOffer(@RequestBody CreateOfferRequest request){
        OfferResponse response = _offerService.createOffer(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Offer cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public void changeOffer(@RequestBody ChangeOfferRequest request){
        _offerService.changeOffer(request);
    }
}
