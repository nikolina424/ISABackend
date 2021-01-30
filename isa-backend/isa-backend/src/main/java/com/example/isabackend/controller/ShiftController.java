package com.example.isabackend.controller;


import com.example.isabackend.dto.request.ShiftRequest;
import com.example.isabackend.dto.request.SpecialShiftRequest;
import com.example.isabackend.dto.response.DermatologistResponse;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.dto.response.ShiftResponse;
import com.example.isabackend.entity.Shift;
import com.example.isabackend.services.IShiftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shifts")
public class ShiftController {
    private final IShiftService _shiftService;

    public ShiftController(IShiftService shiftService) {
        _shiftService = shiftService;
    }

    @GetMapping("/{id}/dermatologist")
    public ResponseEntity<?> getAllShiftByDermatologistId(@PathVariable("id") Long id){
        List<ShiftResponse> shiftResponses = _shiftService.getAllShiftByDermatologistId(id);
        if(shiftResponses != null) {
            return new ResponseEntity<>(shiftResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Shift for this dermatologist doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/pharmacy")
    public ResponseEntity<?> getAllShiftsByPharmacyId(@PathVariable("id") Long id){
        List<ShiftResponse> shiftResponses = _shiftService.getAllShiftsByPharmacyId(id);
        if(shiftResponses != null) {
            return new ResponseEntity<>(shiftResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Shift for this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/special-shift")
    public ResponseEntity<?> getOneDermatologistOnePharmacyShift(@RequestParam("pharmacyId") Long pharmacyId,
                                                                 @RequestParam("dermatologistId") Long dermatologistId){
        ShiftResponse shiftResponses = _shiftService.getOneDermatologistOnePharmacyShift(pharmacyId, dermatologistId);
        if(shiftResponses != null) {
            return new ResponseEntity<>(shiftResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Shift for this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public List<ShiftResponse> getAllShifts(){
        return _shiftService.getAllShifts();
    }

    @PostMapping()
    public ResponseEntity<?>  createShift(@RequestBody ShiftRequest shiftRequest){
        ShiftResponse shiftResponse = _shiftService.createShift(shiftRequest);
        if(shiftResponse != null) {
            return new ResponseEntity<>(shiftResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Shift cannot be created.", HttpStatus.NOT_FOUND);
        }
    }



}
