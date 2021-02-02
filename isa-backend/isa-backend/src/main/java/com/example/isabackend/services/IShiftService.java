package com.example.isabackend.services;

import com.example.isabackend.dto.request.RemoveDermatologistsShiftRequest;
import com.example.isabackend.dto.request.ShiftRequest;
import com.example.isabackend.dto.request.SpecialShiftRequest;
import com.example.isabackend.dto.response.DermatologistResponse;
import com.example.isabackend.dto.response.ShiftResponse;

import java.util.List;

public interface IShiftService {
    List<ShiftResponse> getAllShiftByDermatologistId(Long id);

    List<ShiftResponse> getAllShifts();

    ShiftResponse createShift(ShiftRequest shiftRequest);

    List<ShiftResponse> getAllShiftsByPharmacyId(Long id);

    ShiftResponse getOneDermatologistOnePharmacyShift(Long pharmacyId, Long dermatologistId);

    int removeDermatologistFromPharmacy(RemoveDermatologistsShiftRequest request);
}
