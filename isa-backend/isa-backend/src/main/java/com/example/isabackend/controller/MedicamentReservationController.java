package com.example.isabackend.controller;

import com.example.isabackend.dto.request.GetIdRequest;
import com.example.isabackend.dto.request.MedicamentReservationRequest;
import com.example.isabackend.dto.request.ShiftRequest;
import com.example.isabackend.dto.response.MedicamentReservationResponse;
import com.example.isabackend.dto.response.MedicamentResponse;
import com.example.isabackend.dto.response.ShiftResponse;
import com.example.isabackend.services.IMedicamentReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicament-reservations")
public class MedicamentReservationController {
    private final IMedicamentReservationService _medicamentReservationService;

    public MedicamentReservationController(IMedicamentReservationService medicamentReservationService) {
        _medicamentReservationService = medicamentReservationService;
    }

    @GetMapping("/dropped/{id}/patient")
    public ResponseEntity<?> getDroppedByPatientId(@PathVariable("id") Long id){
        List<MedicamentReservationResponse> responses = _medicamentReservationService.getDroppedByPatientId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Medicament reservations doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/active/{id}/patient")
    public ResponseEntity<?> getActiveByPatientId(@PathVariable("id") Long id){
        List<MedicamentReservationResponse> responses = _medicamentReservationService.getActiveByPatientId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Medicament reservations doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/cancel")
        public boolean cancelReservation(@RequestBody GetIdRequest request){
       return  _medicamentReservationService.cancelReservation(request);
    }

    @PostMapping()
    public ResponseEntity<?>  createReservation(@RequestBody MedicamentReservationRequest request){
        MedicamentReservationResponse response = _medicamentReservationService.createReservation(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Reservation cannot be created.", HttpStatus.NOT_FOUND);
        }
    }
}
