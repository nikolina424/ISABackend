package com.example.isabackend.controller;

import com.example.isabackend.dto.response.DermatologistResponse;
import com.example.isabackend.dto.response.MedicamentResponse;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.services.IDermatologistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dermatologists")
public class DermatologistController {

    private final IDermatologistService _dermatologistService;

    public DermatologistController(IDermatologistService dermatologistService) {
        _dermatologistService = dermatologistService;
    }

    @GetMapping()
    public List<DermatologistResponse> getAllDermatologists(){
        return _dermatologistService.getAllDermatologists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDermatologistById(@PathVariable("id") Long id){
        DermatologistResponse dermatologistResponse = _dermatologistService.getDermatologistById(id);
        if(dermatologistResponse != null) {
            return new ResponseEntity<>(dermatologistResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Dermatologist doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/pharmacy")
    public ResponseEntity<?> getAllDermatologistByPharmacyId(@PathVariable("id") Long id){
        List<DermatologistResponse> dermatologistResponses = _dermatologistService.getAllDermatologistByPharmacyId(id);
        if(dermatologistResponses != null) {
            return new ResponseEntity<>(dermatologistResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Dermatologist in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchDermatologists(@RequestParam("firstName") String firstName, @RequestParam(value="lastName") String lastName){
        return new ResponseEntity<>(_dermatologistService.searchDermatologists(firstName, lastName), HttpStatus.OK);
    }



}
