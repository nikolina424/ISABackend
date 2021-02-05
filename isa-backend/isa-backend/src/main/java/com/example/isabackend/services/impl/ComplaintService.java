package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.AnswerComplaintRequest;
import com.example.isabackend.dto.request.CreateComplaintRequest;
import com.example.isabackend.dto.response.ComplaintResponse;
import com.example.isabackend.entity.*;
import com.example.isabackend.repository.*;
import com.example.isabackend.services.IComplaintService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintService implements IComplaintService {
    private final IComplaintRepository _complaintRepository;
    private final PatientService _patientService;
    private final PharmacistService _pharmacistService;
    private final DermatologistService _dermatologistService;
    private final IPharmacyRepository _pharmacyRepository;
    private final IDermatologistRepository _dermatologistRepository;
    private final IPharmacistRepository _pharmacistRepository;
    private final IPatientRepository _patientRepository;
    private final EmailService _emailService;


    public ComplaintService(IComplaintRepository complaintRepository, PatientService patientService, PharmacistService pharmacistService, DermatologistService dermatologistService, IPharmacyRepository pharmacyRepository, IDermatologistRepository dermatologistRepository, IPharmacistRepository pharmacistRepository, IPatientRepository patientRepository, EmailService emailService) {
        _complaintRepository = complaintRepository;
        _patientService = patientService;
        _pharmacistService = pharmacistService;
        _dermatologistService = dermatologistService;
        _pharmacyRepository = pharmacyRepository;
        _dermatologistRepository = dermatologistRepository;
        _pharmacistRepository = pharmacistRepository;
        _patientRepository = patientRepository;
        _emailService = emailService;
    }


    @Override
    public ComplaintResponse createPharmacyComplaint(CreateComplaintRequest request) {
        Pharmacy pharmacy = _pharmacyRepository.findOneById(request.getComplainingObjectId());
        Complaint complaint = new Complaint();
        complaint.setAnswered(false);
        complaint.setPharmacy(pharmacy);
        complaint.setPatient(_patientRepository.findOneById(request.getPatientId()));
        complaint.setText(request.getText());
        Complaint savedComplaint = _complaintRepository.save(complaint);
        pharmacy.getComplaints().add(savedComplaint);
        _pharmacyRepository.save(pharmacy);
        return mapComplaintToComplaintResponse(savedComplaint);
    }

    @Override
    public ComplaintResponse createPharmacistComplaint(CreateComplaintRequest request) {
        Pharmacist pharmacist = _pharmacistRepository.findOneById(request.getComplainingObjectId());
        Complaint complaint = new Complaint();
        complaint.setAnswered(false);
        complaint.setPharmacist(pharmacist);
        complaint.setPatient(_patientRepository.findOneById(request.getPatientId()));
        complaint.setPharmacy(_pharmacyRepository.findOneById(request.getPharmacyId()));
        complaint.setText(request.getText());
        Complaint savedComplaint = _complaintRepository.save(complaint);
        pharmacist.getComplaints().add(savedComplaint);
        _pharmacistRepository.save(pharmacist);
        return mapComplaintToComplaintResponse(savedComplaint);
    }

    @Override
    public ComplaintResponse createDermatologistComplaint(CreateComplaintRequest request) {
        Dermatologist dermatologist = _dermatologistRepository.findOneById(request.getComplainingObjectId());
        Complaint complaint = new Complaint();
        complaint.setAnswered(false);
        complaint.setDermatologist(dermatologist);
        complaint.setPatient(_patientRepository.findOneById(request.getPatientId()));
        complaint.setText(request.getText());
        complaint.setPharmacy(_pharmacyRepository.findOneById(request.getPharmacyId()));
        Complaint savedComplaint = _complaintRepository.save(complaint);
        dermatologist.getComplaints().add(savedComplaint);
        _dermatologistRepository.save(dermatologist);
        return mapComplaintToComplaintResponse(savedComplaint);
    }

    @Override
    public boolean answer(AnswerComplaintRequest request) {
        Complaint complaint = _complaintRepository.findOneById(request.getComplaintId());
        complaint.setAnswered(true);
        _complaintRepository.save(complaint);

        _emailService.answerOnComplaint(request);
        return true;
    }

    @Override
    public List<ComplaintResponse> getAllDermatologistComplaints() {

        List<Complaint> complaints = _complaintRepository.findAll();
        List<Complaint> finalComplaints = new ArrayList<>();
        for(Complaint c: complaints){
                    if(c.getDermatologist() != null) {
                        if(c.isAnswered() == false){
                            finalComplaints.add(c);
                        }
                    }
        }
        return mapComplaintsToComplaintsResponses(finalComplaints);
    }

    @Override
    public List<ComplaintResponse> getAllPharmacistsComplaints() {
        List<Complaint> complaints = _complaintRepository.findAll();
        List<Complaint> finalComplaints = new ArrayList<>();
        for(Complaint c: complaints){
                    if(c.getPharmacist() != null) {
                        if(c.isAnswered() == false){
                            finalComplaints.add(c);
                        }
                    }
        }
        return mapComplaintsToComplaintsResponses(finalComplaints);
    }

    @Override
    public List<ComplaintResponse> getAllPharmacyComplaints() {
        List<Complaint> complaints = _complaintRepository.findAll();
        List<Complaint> finalComplaints = new ArrayList<>();
        for(Complaint c: complaints){
            if (c.getPharmacy() != null) {
                        if(c.isAnswered() == false){
                            finalComplaints.add(c);
                        }
                    }
        }
        return mapComplaintsToComplaintsResponses(finalComplaints);
    }

    private List<ComplaintResponse> mapComplaintsToComplaintsResponses(List<Complaint> finalComplaints) {
        ComplaintResponse complaintResponse = new ComplaintResponse();
        List<ComplaintResponse> complaintResponses = new ArrayList<>();
        for (Complaint complaint: finalComplaints) {
            complaintResponse =  mapComplaintToComplaintResponse(complaint);
            complaintResponses.add(complaintResponse);
        }
        return complaintResponses;
    }

    private ComplaintResponse mapComplaintToComplaintResponse(Complaint complaint) {
        ComplaintResponse complaintResponse = new ComplaintResponse();
        complaintResponse.setId(complaint.getId());
        complaintResponse.setAnswered(complaint.isAnswered());
        complaintResponse.setText(complaint.getText());
        complaintResponse.setPatientResponse(_patientService.mapPatientToPatientResponse(complaint.getPatient()));
        if(complaint.getPharmacist() != null){
            complaintResponse.setPharmacistResponse(_pharmacistService.mapPharmacistToPharmacistResponse(complaint.getPharmacist()));
        }else if(complaint.getDermatologist() != null){
            complaintResponse.setDermatologistResponse(_dermatologistService.mapDermatologistToDermatologistResponse(complaint.getDermatologist()));
        }

        return complaintResponse;
    }
}
