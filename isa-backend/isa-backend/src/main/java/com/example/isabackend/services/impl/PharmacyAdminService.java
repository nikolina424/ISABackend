package com.example.isabackend.services.impl;

import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.dto.response.PharmacyAdminResponse;
import com.example.isabackend.entity.Patient;
import com.example.isabackend.entity.PharmacyAdmin;
import com.example.isabackend.repository.IPharmacyAdminRepository;
import com.example.isabackend.services.IPharmacyAdminService;
import org.springframework.stereotype.Service;

@Service
public class PharmacyAdminService implements IPharmacyAdminService {

    private final IPharmacyAdminRepository _pharmacyAdminRepository;

    public PharmacyAdminService(IPharmacyAdminRepository pharmacyAdminRepository) {
        _pharmacyAdminRepository = pharmacyAdminRepository;
    }

    @Override
    public PharmacyAdminResponse getPharmacyAdminById(Long id) {
        PharmacyAdmin pharmacyAdmin = _pharmacyAdminRepository.findOneById(id);
        if(pharmacyAdmin != null) {
            return mapPharmacyAdminToPharmacyAdminResponse(pharmacyAdmin);
        } else {
            return null;
        }
    }

    @Override
    public PharmacyAdminResponse getPharmacyAdminByUserId(Long id) {
        PharmacyAdmin pharmacyAdmin = _pharmacyAdminRepository.findOneByUser_id(id);
        if(pharmacyAdmin != null) {
            return mapPharmacyAdminToPharmacyAdminResponse(pharmacyAdmin);
        } else {
            return null;
        }
    }

    private PharmacyAdminResponse mapPharmacyAdminToPharmacyAdminResponse(PharmacyAdmin pharmacyAdmin) {
        PharmacyAdminResponse pharmacyAdminResponse = new PharmacyAdminResponse();
        pharmacyAdminResponse.setId(pharmacyAdmin.getId());
        pharmacyAdminResponse.setPharmacyId(pharmacyAdmin.getPharmacy().getId());
        pharmacyAdminResponse.setFirstName(pharmacyAdmin.getFirstName());
        pharmacyAdminResponse.setLastName(pharmacyAdmin.getLastName());
        return pharmacyAdminResponse;
    }
}
