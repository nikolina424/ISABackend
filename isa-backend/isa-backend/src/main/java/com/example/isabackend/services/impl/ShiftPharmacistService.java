package com.example.isabackend.services.impl;

import com.example.isabackend.dto.response.ShiftPharmacistResponse;
import com.example.isabackend.entity.ShiftPharmacist;
import com.example.isabackend.repository.IShiftPharmacistRepository;
import com.example.isabackend.services.IShiftPharmacistService;
import org.springframework.stereotype.Service;

@Service
public class ShiftPharmacistService implements IShiftPharmacistService {
    private final IShiftPharmacistRepository _shiftPharmacyRepository;
    private final PharmacyService _pharmacyService;
    private final PharmacistService _pharmacistService;

    public ShiftPharmacistService(IShiftPharmacistRepository shiftPharmacyRepository, PharmacyService pharmacyService, PharmacistService pharmacistService) {
        _shiftPharmacyRepository = shiftPharmacyRepository;
        _pharmacyService = pharmacyService;
        _pharmacistService = pharmacistService;
    }

    public ShiftPharmacistResponse mapShiftPharmacistToShiftPharmacistResponse(ShiftPharmacist shiftPharmacist) {
        ShiftPharmacistResponse shiftResponse = new ShiftPharmacistResponse();
        shiftResponse.setId(shiftPharmacist.getId());
        shiftResponse.setStartShift(shiftPharmacist.getStartShift());
        shiftResponse.setEndShift(shiftPharmacist.getEndShift());
        shiftResponse.setPharmacyResponse(_pharmacyService.mapPharmacyToPharmacyResponse(shiftPharmacist.getPharmacy()));
        shiftResponse.setPharmacistResponse(_pharmacistService.mapPharmacistToPharmacistResponse(shiftPharmacist.getPharmacist()));
        return shiftResponse;
    }
}
