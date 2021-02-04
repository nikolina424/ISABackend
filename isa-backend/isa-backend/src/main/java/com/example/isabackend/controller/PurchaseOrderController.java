package com.example.isabackend.controller;

import com.example.isabackend.dto.request.CreateOrderMedicamentRequest;
import com.example.isabackend.dto.request.CreatePurchaseOrderRequest;
import com.example.isabackend.dto.response.OrderMedicamentResponse;
import com.example.isabackend.dto.response.PurchaseOrderResponse;
import com.example.isabackend.services.IPurchaseOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
