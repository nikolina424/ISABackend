package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.AllergyRequest;
import com.example.isabackend.dto.request.GetIdRequest;
import com.example.isabackend.dto.request.UpdatePatientRequest;
import com.example.isabackend.dto.response.MedicamentResponse;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.entity.Medicament;
import com.example.isabackend.entity.Patient;
import com.example.isabackend.entity.User;
import com.example.isabackend.repository.IMedicamentRepository;
import com.example.isabackend.repository.IPatientRepository;
import com.example.isabackend.repository.IUserRepository;
import com.example.isabackend.services.IEmailService;
import com.example.isabackend.services.IPatientService;
import com.example.isabackend.util.enums.RequestStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService implements IPatientService {
    private final IPatientRepository _patientRepository;
    private final IUserRepository _userRepository;
    private final IEmailService _emailService;
    private final IMedicamentRepository _medicamentRepository;
    private final MedicamentService _medicamentService;

    public PatientService(IPatientRepository patientRepository, IUserRepository userRepository, IEmailService emailService, IMedicamentRepository medicamentRepository, MedicamentService medicamentService) {
        _patientRepository = patientRepository;
        _userRepository = userRepository;
        _emailService = emailService;
        _medicamentRepository = medicamentRepository;
        _medicamentService = medicamentService;
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

    @Override
    public void approveRegistrationRequest(GetIdRequest request) {
        Patient patient = _patientRepository.findOneById(request.getId());
        patient.setRequestStatus(RequestStatus.APPROVED);
        Patient savedPatient = _patientRepository.save(patient);

        _emailService.approveRegistrationMail(savedPatient);
    }

    @Override
    public void denyRegistrationRequest(GetIdRequest request) {
        Patient patient = _patientRepository.findOneById(request.getId());
        patient.setRequestStatus(RequestStatus.DENIED);
        Patient savedPatient = _patientRepository.save(patient);
        _emailService.denyRegistrationMail(savedPatient);
    }

    @Override
    public void confirmRegistrationRequest(GetIdRequest request) {
        Patient patient = _patientRepository.findOneById(request.getId());
        patient.setRequestStatus(RequestStatus.CONFIRMED);
        _patientRepository.save(patient);
    }

    @Override
    public List<PatientResponse> getRegistrationRequests() {
        List<Patient> patients = _patientRepository.findAllByRequestStatus(RequestStatus.PENDING);
        List<PatientResponse> patientResponses = new ArrayList<>();
        for (Patient patient: patients) {
            PatientResponse patientResponse = mapPatientToPatientResponse(patient);
            patientResponses.add(patientResponse);
        }
        return patientResponses;
    }

    @Override
    public void addNewAlergy(Long id, AllergyRequest request) {
        Patient patient = _patientRepository.findOneById(id);
        Medicament medicament = _medicamentRepository.findOneById(request.getId());
        List<Medicament> medicamentSet = patient.getMedicaments();
        medicamentSet.add(medicament);
        _patientRepository.save(patient);
    }

    @Override
    public List<MedicamentResponse> getAvailableMeds(Long id) {
        Patient patient = _patientRepository.findOneById(id);
        List<Medicament> allergies = patient.getMedicaments();
        List<Medicament> allMedicaments = _medicamentRepository.findAll();
        List<Medicament> availableMedicaments = new ArrayList<>();
        for (Medicament m: allMedicaments) {
                if(!allergies.contains(m)){
                    availableMedicaments.add(m);
                }
        }
        List<MedicamentResponse> m = _medicamentService.mapMedicamentListToMedicamentResponseList(availableMedicaments);
        return m;
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
