package com.example.isabackend.controller;

import com.example.isabackend.dto.request.GetIdRequest;
import com.example.isabackend.dto.request.UpdatePatientRequest;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.dto.response.TempResponse;
import com.example.isabackend.services.IPatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final IPatientService _patientService;

    public PatientController(IPatientService patientService) {
        _patientService = patientService;
    }

    @GetMapping()
    public List<PatientResponse> getAllPatients(){
        return _patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatient(@PathVariable("id") Long id){
        PatientResponse patientResponse = _patientService.getPatientById(id);
        if(patientResponse != null) {
            return new ResponseEntity<>(patientResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public void updatePatient(@PathVariable("id")Long id, @RequestBody UpdatePatientRequest request){
        _patientService.updatePatient(id, request);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable("id") Long id){
        TempResponse temp = new TempResponse();
        temp.setText("Deleted");
        if(_patientService.deletePatientById(id)){
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Patient doesn't exist.", HttpStatus.NOT_FOUND);
        }
    }


    //admin odobrava
    @PutMapping("/approve")
    //@PreAuthorize("hasAuthority('REGISTER')")
    public void approveRegistrationRequest(@RequestBody GetIdRequest request){
        _patientService.approveRegistrationRequest(request);
    }

    @PutMapping("/deny")
    //@PreAuthorize("hasAuthority('REGISTER')")
    public void denyRegistrationRequest(@RequestBody GetIdRequest request){
        _patientService.denyRegistrationRequest(request);
    }

    //user potvrdjuje na mail-u
    @PutMapping("/confirm")
    public void confirmRegistrationRequest(@RequestBody GetIdRequest request){
        _patientService.confirmRegistrationRequest(request);
    }
}
