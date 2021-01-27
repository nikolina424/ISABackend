package com.example.isabackend.controller;

import com.example.isabackend.dto.response.MedicamentResponse;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.services.IMedicamentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicaments")
public class MedicamentController {

    private final IMedicamentService _medicamentService;

    public MedicamentController(IMedicamentService medicamentService) {
        _medicamentService = medicamentService;
    }

    @GetMapping()
    public List<MedicamentResponse> getAllPatients(){
        return _medicamentService.getAllMedicaments();
    }
}
