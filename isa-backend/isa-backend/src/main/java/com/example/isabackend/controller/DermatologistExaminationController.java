package com.example.isabackend.controller;

import com.example.isabackend.dto.request.CreateAvailableExaminationRequest;
import com.example.isabackend.dto.response.DermatologistExaminationResponse;
import com.example.isabackend.dto.response.DermatologistResponse;
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
    public ResponseEntity<?> createAvailableExamination(@RequestParam(required=false,name="startTimeExamination") String startTimeExamination,
                                                        @RequestParam(required=false,name="endTimeExamination") String endTimeExamination,
                                                        @RequestParam(required=false,name="dateExamination") String dateExamination,
                                                        @RequestParam(required=false,name="pharmacyId") Long pharmacyId,
                                                        @RequestParam(required=false,name="dermatologistId") Long dermatologistId,
                                                        @RequestParam(required=false,name="price") Double price){
        System.out.println("Pozvali su me");
        System.out.println(startTimeExamination);
        DermatologistExaminationResponse dermatologistExaminationResponse = _dermatologistExaminationService.createAvailableExamination(startTimeExamination,endTimeExamination, dateExamination, pharmacyId, dermatologistId, price);

        if(dermatologistExaminationResponse != null) {
            return new ResponseEntity<>(dermatologistExaminationResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Dermatologist examination cannot be created.", HttpStatus.NOT_FOUND);
        }
    }
}
