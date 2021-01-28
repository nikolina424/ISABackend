package com.example.isabackend.services;

import com.example.isabackend.dto.request.ShiftRequest;
import com.example.isabackend.dto.response.ShiftResponse;

import java.util.List;

public interface IShiftService {
    List<ShiftResponse> getAllShiftByDermatologistId(Long id);

    List<ShiftResponse> getAllShifts();

    ShiftResponse createShift(ShiftRequest shiftRequest);
}
