package com.example.isabackend.controller;

import com.example.isabackend.dto.request.*;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.dto.response.PharmacyResponse;
import com.example.isabackend.dto.response.TempResponse;
import com.example.isabackend.services.IPharmacyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {
    private final IPharmacyService _pharmacyService;

    public PharmacyController(IPharmacyService pharmacyService) {
        _pharmacyService = pharmacyService;
    }

    @PutMapping("/{id}")
    public void updatePharmacy(@PathVariable("id")Long id, @RequestBody UpdatePharmacyRequest request){
        _pharmacyService.updatePharmacy(id, request);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPharmacies(@RequestParam("name") String name){
        return new ResponseEntity<>(_pharmacyService.searchPharmacies(name), HttpStatus.OK);
    }

    @GetMapping()
    public List<PharmacyResponse> getAllPharmacies(){
        return _pharmacyService.getAllPharmacies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPharmacy(@PathVariable("id") Long id){
        PharmacyResponse pharmacyResponse = _pharmacyService.getPharmacyById(id);
        if(pharmacyResponse != null) {
            return new ResponseEntity<>(pharmacyResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/medicament/{id}")
    public ResponseEntity<?> getPharmaciesByMedicamentId(@PathVariable("id") Long id){
        List<PharmacyResponse> pharmacyResponses = _pharmacyService.getPharmaciesByMedicamentId(id);
        if(pharmacyResponses != null) {
            return new ResponseEntity<>(pharmacyResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Pharmacies doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register-pharmacy")
    public ResponseEntity<?> registerPharmacy(@RequestBody PharmacyRequest request){
        TempResponse temp = new TempResponse();
        temp.setText("Registered pharmacy");
        if(_pharmacyService.registerPharmacy(request)){
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Pharmacy already exists !", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/remove/medicament")
    public boolean removeMedicament(@RequestBody RemoveFromPharmacyRequest request){
        return _pharmacyService.removeMedicament(request);
    }

    @PutMapping("/remove/pharmacist")
    public boolean removePharmacist(@RequestBody RemoveFromPharmacyRequest request){
        return _pharmacyService.removePharmacist(request);
    }

    @PutMapping("/remove/dermatologist")
    public boolean removeDermatologist(@RequestBody RemoveFromPharmacyRequest request){
        return _pharmacyService.removeDermatologist(request);
    }
}
