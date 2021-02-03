package com.example.isabackend.controller;

import com.example.isabackend.dto.request.UpdateSupplierRequest;
import com.example.isabackend.dto.request.UpdateSystemAdminRequest;
import com.example.isabackend.dto.response.SupplierResponse;
import com.example.isabackend.dto.response.SystemAdminResponse;
import com.example.isabackend.services.ISupplierService;
import com.example.isabackend.services.ISystemAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system-admins")
public class SystemAdminController {
    private final ISystemAdminService _saService;

    public SystemAdminController(ISystemAdminService saService) {
        _saService = saService;
    }

    @PutMapping("/{id}")
    public void updateSystemAdmin(@PathVariable("id")Long id, @RequestBody UpdateSystemAdminRequest request){
        _saService.updateSystemAdmin(id, request);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSystemAdmin(@PathVariable("id") Long id){
        SystemAdminResponse response = _saService.getSystemAdmin(id);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("System admin doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }
}
