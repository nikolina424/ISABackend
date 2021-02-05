package com.example.isabackend.controller;

import com.example.isabackend.dto.request.CreateComplaintRequest;
import com.example.isabackend.dto.request.CreateOrderMedicamentRequest;
import com.example.isabackend.dto.response.ComplaintResponse;
import com.example.isabackend.dto.response.OrderMedicamentResponse;
import com.example.isabackend.dto.response.PurchaseOrderResponse;
import com.example.isabackend.services.IOrderMedicamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-medicaments")
public class OrderMedicamentController {

    private final IOrderMedicamentService _omService;

    public OrderMedicamentController(IOrderMedicamentService omService) {
        _omService = omService;
    }

    @PostMapping()
    public ResponseEntity<?> createOrderMedicament(@RequestBody CreateOrderMedicamentRequest request){
        OrderMedicamentResponse response = _omService.createOrderMedicament(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Order medicament cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/purchase-order")
    public ResponseEntity<?> getAllByOrderId(@PathVariable("id") Long id){
        List<OrderMedicamentResponse> responses = _omService.getAllByOrderId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Medicament for this order doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
}
