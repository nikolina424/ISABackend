package com.example.isabackend.controller;

import com.example.isabackend.dto.response.MedicamentResponse;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.services.IMedicamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicaments")
public class MedicamentController {

    private final IMedicamentService _medicamentService;

    public MedicamentController(IMedicamentService medicamentService) {
        _medicamentService = medicamentService;
    }

    @GetMapping()
    public List<MedicamentResponse> getAllMedicaments(){
        return _medicamentService.getAllMedicaments();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchMedicaments(@RequestParam("name") String name, @RequestParam(value="type") String type){
        return new ResponseEntity<>(_medicamentService.searchMedicaments(name, type), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMedicamentById(@PathVariable("id") Long id){
        MedicamentResponse medicamentResponse = _medicamentService.getMedicamentById(id);
        if(medicamentResponse != null) {
            return new ResponseEntity<>(medicamentResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Medicament doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/not-belong/{id}/pharmacy")
    public ResponseEntity<?> getMedicamentsDoesntBelongToPharmacy(@PathVariable("id") Long id){
        List<MedicamentResponse> medicamentResponses = _medicamentService.getMedicamentsDoesntBelongToPharmacy(id);
        if(medicamentResponses != null) {
            return new ResponseEntity<>(medicamentResponses, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Medicaments doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }


}
