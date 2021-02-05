package com.example.isabackend.controller;

import com.example.isabackend.dto.request.CreateOrderMedicamentRequest;
import com.example.isabackend.dto.request.CreatePurchaseOrderRequest;
import com.example.isabackend.dto.response.OrderMedicamentResponse;
import com.example.isabackend.dto.response.PharmacistResponse;
import com.example.isabackend.dto.response.PurchaseOrderResponse;
import com.example.isabackend.services.IPurchaseOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    private final IPurchaseOrderService _poService;

    public PurchaseOrderController(IPurchaseOrderService poService) {
        _poService = poService;
    }

    @PostMapping()
    public ResponseEntity<?> createPurchaseOrder(@RequestBody CreatePurchaseOrderRequest request){
        PurchaseOrderResponse response = _poService.createPurchaseOrder(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Purchase order cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/pharmacy")
    public ResponseEntity<?> getAllByPharmacyId(@PathVariable("id") Long id){
        List<PurchaseOrderResponse> responses = _poService.getAllByPharmacyId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Purchase order in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<?> getAllByActiveStatus(){
        List<PurchaseOrderResponse> responses = _poService.getAllByActiveStatus();
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Purchase order in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
}
