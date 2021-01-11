package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.UpdatePatientRequest;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.entity.Patient;
import com.example.isabackend.entity.User;
import com.example.isabackend.repository.IPatientRepository;
import com.example.isabackend.repository.IUserRepository;
import com.example.isabackend.services.IPatientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService implements IPatientService {
    private final IPatientRepository _patientRepository;
    private final IUserRepository _userRepository;

    public PatientService(IPatientRepository patientRepository, IUserRepository userRepository) {
        _patientRepository = patientRepository;
        _userRepository = userRepository;
    }

    @Override
    public List<PatientResponse> getAllPatients() {
        List<Patient> patients = _patientRepository.findAllByDeleted(false);
        List<PatientResponse> patientResponses = new ArrayList<>();
        for (Patient patient: patients) {
            PatientResponse patientResponse = mapPatientToPatientResponse(patient);
            patientResponses.add(patientResponse);
        }
        return patientResponses;
    }

    @Override
    public PatientResponse getPatientById(Long id) {
        Patient patient = _patientRepository.findOneById(id);
        if(patient != null) {
            return mapPatientToPatientResponse(patient);
        } else {
            return null;
        }
    }

    @Override
    public void updatePatient(Long id, UpdatePatientRequest request) {
        Patient patient = _patientRepository.findOneById(id);
        if(request.getAddress() != null)
            patient.setAddress(request.getAddress());
        if(request.getFirstName() != null)
            patient.setFirstName(request.getFirstName());
        if(request.getLastName() != null)
            patient.setLastName(request.getLastName());
        if(request.getNumber() != null)
            patient.setNumber(request.getNumber());
        if(request.getCity() != null)
            patient.setCity(request.getCity());
        if(request.getCountry() != null)
            patient.setCountry(request.getCountry());

        _patientRepository.save(patient);
    }

    @Override
    public boolean deletePatientById(Long id) {
        Patient patient = _patientRepository.findOneById(id);
        if(patient != null){
            User user = _userRepository.findOneById(patient.getUser().getId());
            user.setAuthorities(null);
            _userRepository.deleteById(user.getId());
            return true;
        }
        return false;
    }


    private PatientResponse mapPatientToPatientResponse(Patient patient){
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setId(patient.getId());
        patientResponse.setAddress(patient.getAddress());
        patientResponse.setFirstName(patient.getFirstName());
        patientResponse.setLastName(patient.getLastName());
        patientResponse.setCity(patient.getCity());
        patientResponse.setUsername(patient.getUser().getUsername());
        patientResponse.setNumber(patient.getNumber());
        patientResponse.setUserRole(patient.getUser().getUserRole().toString());
        return patientResponse;
    }
}
