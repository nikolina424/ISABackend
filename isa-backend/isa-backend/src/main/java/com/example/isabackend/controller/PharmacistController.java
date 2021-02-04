package com.example.isabackend.controller;

import com.example.isabackend.dto.request.PharmacistRequest;
import com.example.isabackend.dto.request.ShiftRequest;
import com.example.isabackend.dto.request.UpdatePharmacistRequest;
import com.example.isabackend.dto.response.*;
import com.example.isabackend.services.IPharmacistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacists")
public class PharmacistController {

    private final IPharmacistService _pharmacistService;

    public PharmacistController(IPharmacistService pharmacistService) {
        _pharmacistService = pharmacistService;
    }


    @GetMapping("/search")
    public ResponseEntity<?> searchPharmacist(@RequestParam("firstName") String firstName, @RequestParam(value="lastName") String lastName){
        return new ResponseEntity<>(_pharmacistService.searchPharmacist(firstName, lastName), HttpStatus.OK);
    }

    @GetMapping("/{id}/pharmacy")
    public ResponseEntity<?> getAllPharmacistByPharmacyId(@PathVariable("id") Long id){
        List<PharmacistResponse> pharmacistResponses = _pharmacistService.getAllPharmacistByPharmacyId(id);
        if(pharmacistResponses != null) {
            return new ResponseEntity<>(pharmacistResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Pharmacists in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/date")
    public ResponseEntity<?> getPharmacistsDate(@RequestParam("dateExamination") String dateExamination,
                                                 @RequestParam("startExamination") String startExamination,
                                                 @RequestParam("endExamination") String endExamination,
                                                 @RequestParam("pharmacyId") Long pharmacyId){
        List<PharmacistResponse> pharmacistResponses = _pharmacistService.getPharmacistsDate(dateExamination,startExamination,endExamination , pharmacyId);
        if(pharmacistResponses != null) {
            return new ResponseEntity<>(pharmacistResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Pharmacists doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPharmacist(@PathVariable("id") Long id){
        PharmacistResponse response = _pharmacistService.getPharmacist(id);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Pharmacist doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void updatePharmacist(@PathVariable("id")Long id, @RequestBody UpdatePharmacistRequest request){
        _pharmacistService.updatePharmacist(id, request);

    }
}
