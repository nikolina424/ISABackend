package com.example.isabackend.controller;
import com.example.isabackend.dto.request.UpdateSupplierRequest;
import com.example.isabackend.dto.response.PharmacistResponse;
import com.example.isabackend.dto.response.SupplierResponse;
import com.example.isabackend.services.ISupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    private final ISupplierService _supplierService;

    public SupplierController(ISupplierService supplierService) {
        _supplierService = supplierService;
    }

    @PutMapping("/{id}")
    public void updateSupplier(@PathVariable("id")Long id, @RequestBody UpdateSupplierRequest request){
        _supplierService.updateSupplier(id, request);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplier(@PathVariable("id") Long id){
        SupplierResponse response = _supplierService.getSupplier(id);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Supplier doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
}
