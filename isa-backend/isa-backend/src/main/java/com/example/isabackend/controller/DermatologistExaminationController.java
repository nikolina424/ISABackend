package com.example.isabackend.controller;

import com.example.isabackend.dto.request.CreateAvailableExaminationRequest;
import com.example.isabackend.dto.request.GetIdRequest;
import com.example.isabackend.dto.request.ReserveDermatologistExaminationRequest;
import com.example.isabackend.dto.response.DermatologistExaminationResponse;
import com.example.isabackend.services.IDermatologistExaminationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dermatologist-examinations")
public class DermatologistExaminationController {
    private final IDermatologistExaminationService _dermatologistExaminationService;

    public DermatologistExaminationController(IDermatologistExaminationService dermatologistExaminationService) {
        _dermatologistExaminationService = dermatologistExaminationService;
    }

    @GetMapping("/{id}/pharmacy")
    public ResponseEntity<?> getAllAvailableExaminationsByPharmacyId(@PathVariable("id") Long id){
        List<DermatologistExaminationResponse> responses = _dermatologistExaminationService.getAllAvailableExaminationsByPharmacyId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Examinations in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?>  createAvailableExamination(@RequestBody CreateAvailableExaminationRequest request){
        System.out.println(request);
        DermatologistExaminationResponse response = _dermatologistExaminationService.createAvailableExamination(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Dermatologist examination cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reserve")
    public boolean reserveExamination(@RequestBody ReserveDermatologistExaminationRequest request){
        return  _dermatologistExaminationService.reserveExamination(request);
    }

    @GetMapping("/dropped/{id}/patient")
    public ResponseEntity<?> getAllDroppedReservationByPatientId(@PathVariable("id") Long id){
        List<DermatologistExaminationResponse> responses = _dermatologistExaminationService.getAllDroppedReservationByPatientId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Examinations in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/active/{id}/patient")
    public ResponseEntity<?> getAllActiveReservationByPatientId(@PathVariable("id") Long id){
        List<DermatologistExaminationResponse> responses = _dermatologistExaminationService.getAllActiveReservationByPatientId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Examinations in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/cancel")
    public boolean cancelReservation(@RequestBody GetIdRequest request){
        return  _dermatologistExaminationService.cancelReservation(request);
    }
}
