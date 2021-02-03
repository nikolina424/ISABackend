package com.example.isabackend.controller;

import com.example.isabackend.dto.request.AnswerComplaintRequest;
import com.example.isabackend.dto.request.CreateComplaintRequest;
import com.example.isabackend.dto.request.GetIdRequest;
import com.example.isabackend.dto.request.ShiftRequest;
import com.example.isabackend.dto.response.ComplaintResponse;
import com.example.isabackend.dto.response.ShiftResponse;
import com.example.isabackend.services.IComplaintService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final IComplaintService _complaintService;

    public ComplaintController(IComplaintService complaintService) {
        _complaintService = complaintService;
    }

    @GetMapping("/{id}/pharmacy")
    public ResponseEntity<?> getAllComplaintsByPharmacyId(@PathVariable("id") Long id){
        List<ComplaintResponse> responses = _complaintService.getAllComplaintsByPharmacyId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Complaint in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/dermatologists/{id}/pharmacy")
    public ResponseEntity<?> getAllComplaintsOnDermatologistsByPharmacyId(@PathVariable("id") Long id){
        List<ComplaintResponse> responses = _complaintService.getAllComplaintsOnDermatologistsByPharmacyId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Complaint in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pharmacists/{id}/pharmacy")
    public ResponseEntity<?> getAllComplaintsOnPharmacistsByPharmacyId(@PathVariable("id") Long id){
        List<ComplaintResponse> responses = _complaintService.getAllComplaintsOnPharmacistsByPharmacyId(id);
        if(responses != null) {
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Complaint in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dermatologist")
    public ResponseEntity<?>  createDermatologistComplaint(@RequestBody CreateComplaintRequest request){
        ComplaintResponse response = _complaintService.createDermatologistComplaint(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Complaint cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/pharmacist")
    public ResponseEntity<?>  createPharmacistComplaint(@RequestBody CreateComplaintRequest request){
        ComplaintResponse response = _complaintService.createPharmacistComplaint(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Complaint cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/pharmacy")
    public ResponseEntity<?>  createPharmacyComplaint(@RequestBody CreateComplaintRequest request){
        ComplaintResponse response = _complaintService.createPharmacyComplaint(request);
        if(response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Complaint cannot be created.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/answer")
    public boolean answer(@RequestBody AnswerComplaintRequest request){
        return  _complaintService.answer(request);
    }


}
