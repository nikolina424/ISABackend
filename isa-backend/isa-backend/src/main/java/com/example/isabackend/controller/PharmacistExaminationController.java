package com.example.isabackend.controller;

import com.example.isabackend.dto.request.GetIdRequest;
import com.example.isabackend.dto.response.DermatologistExaminationResponse;
import com.example.isabackend.dto.response.PharmacistExaminationResponse;
import com.example.isabackend.entity.DermatologistExamination;
import com.example.isabackend.services.IPharmacistExaminationService;
import com.example.isabackend.util.enums.ExaminationStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pharmacist-examinations")
public class PharmacistExaminationController {
    private final IPharmacistExaminationService _peService;

    public PharmacistExaminationController(IPharmacistExaminationService peService) {
        _peService = peService;
    }

    @GetMapping("/dropped/{id}/patient")
    public ResponseEntity<?> getAllDroppedReservationByPatientId(@PathVariable("id") Long id){
        List<PharmacistExaminationResponse> responses = _peService.getAllDroppedReservationByPatientId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Examinations for this patient doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/active/{id}/patient")
    public ResponseEntity<?> getAllActiveReservationsByPatientId(@PathVariable("id") Long id){
        List<PharmacistExaminationResponse> responses = _peService.getAllActiveReservationsByPatientId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Examinations for this patient doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/cancel")
    public boolean cancelReservation(@RequestBody GetIdRequest request){
        return  _peService.cancelReservation(request);
    }

}
