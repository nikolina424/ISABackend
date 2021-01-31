package com.example.isabackend.controller;

import com.example.isabackend.dto.request.CreateAvailableExaminationRequest;
import com.example.isabackend.dto.request.ShiftRequest;
import com.example.isabackend.dto.response.DermatologistExaminationResponse;
import com.example.isabackend.dto.response.DermatologistResponse;
import com.example.isabackend.dto.response.ShiftResponse;
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
}
