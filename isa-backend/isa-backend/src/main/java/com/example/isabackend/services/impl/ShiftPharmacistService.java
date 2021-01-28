package com.example.isabackend.services.impl;

import com.example.isabackend.repository.IShiftPharmacistRepository;
import com.example.isabackend.services.IShiftPharmacistService;
import org.springframework.stereotype.Service;

@Service
public class ShiftPharmacistService implements IShiftPharmacistService {
    private final IShiftPharmacistRepository _shiftPharmacyRepository;

    public ShiftPharmacistService(IShiftPharmacistRepository shiftPharmacyRepository) {
        _shiftPharmacyRepository = shiftPharmacyRepository;
    }
}
