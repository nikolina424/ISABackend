package com.example.isabackend.controller;


import com.example.isabackend.dto.request.*;
import com.example.isabackend.dto.response.*;
import com.example.isabackend.entity.PharmacyMedicament;
import com.example.isabackend.services.IPharmacyMedicamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacy-medicaments")
public class PharmacyMedicamentController {
    private final IPharmacyMedicamentService _pharmacyMedicamentService;

    public PharmacyMedicamentController(IPharmacyMedicamentService pharmacyMedicamentService) {
        _pharmacyMedicamentService = pharmacyMedicamentService;
    }

    @PostMapping()
    public ResponseEntity<?> addMedicamentToPharmacy(@RequestBody AddMedicamentToPharmacyRequest shiftRequest){
        PharmacyMedicamentResponse pharmacyMedicamentResponse = _pharmacyMedicamentService.addMedicamentToPharmacy(shiftRequest);
        if(pharmacyMedicamentResponse != null) {
            return new ResponseEntity<>(pharmacyMedicamentResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("You cannot add this medicament in your pharmacy", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPharmacyMedicaments(@RequestParam("name") String name, @RequestParam(value="type") String type){
        return new ResponseEntity<>(_pharmacyMedicamentService.searchPharmacyMedicaments(name, type), HttpStatus.OK);
    }


    @GetMapping("/{id}/pharmacy")
    public ResponseEntity<?> getAllMedicamentsByPharmacyId(@PathVariable("id") Long id){
        List<PharmacyMedicamentResponse> pharmacyMedicamentResponses = _pharmacyMedicamentService.getAllMedicamentsByPharmacyId(id);
        if(pharmacyMedicamentResponses != null) {
            return new ResponseEntity<>(pharmacyMedicamentResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Medicament in this pharmacy doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPharmacyMedicament(@PathVariable("id") Long id){
        PharmacyMedicamentResponse pharmacyMedicamentResponse = _pharmacyMedicamentService.getPharmacyMedicament(id);
        if(pharmacyMedicamentResponse != null) {
            return new ResponseEntity<>(pharmacyMedicamentResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Medicament doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void updatePharmacyMedicament(@PathVariable("id")Long id, @RequestBody UpdatePharmacyMedicamentRequest request){
        _pharmacyMedicamentService.updatePharmacyMedicament(id, request);
    }

    @PostMapping("/remove")
    public boolean  removeMedicamentFromPharmacy(@RequestBody RemoveFromPharmacyRequest request){
        return _pharmacyMedicamentService.removeMedicamentFromPharmacy(request);
    }

}
