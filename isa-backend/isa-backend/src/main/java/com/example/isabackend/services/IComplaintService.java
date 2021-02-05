package com.example.isabackend.services;

import com.example.isabackend.dto.request.AnswerComplaintRequest;
import com.example.isabackend.dto.request.CreateComplaintRequest;
import com.example.isabackend.dto.response.ComplaintResponse;

import java.util.List;

public interface IComplaintService {

    ComplaintResponse createPharmacyComplaint(CreateComplaintRequest request);

    ComplaintResponse createPharmacistComplaint(CreateComplaintRequest request);

    ComplaintResponse createDermatologistComplaint(CreateComplaintRequest request);

    boolean answer(AnswerComplaintRequest request);

    List<ComplaintResponse> getAllDermatologistComplaints();

    List<ComplaintResponse> getAllPharmacistsComplaints();

    List<ComplaintResponse> getAllPharmacyComplaints();
}
