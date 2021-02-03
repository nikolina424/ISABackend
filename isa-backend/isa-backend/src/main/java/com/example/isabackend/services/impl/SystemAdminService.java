package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.UpdateSystemAdminRequest;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.dto.response.SystemAdminResponse;
import com.example.isabackend.entity.Patient;
import com.example.isabackend.entity.Supplier;
import com.example.isabackend.entity.SystemAdmin;
import com.example.isabackend.repository.ISystemAdminRepository;
import com.example.isabackend.services.ISystemAdminService;
import org.springframework.stereotype.Service;

@Service
public class SystemAdminService implements ISystemAdminService {
    private final ISystemAdminRepository _saRepository;

    public SystemAdminService(ISystemAdminRepository saRepository) {
        _saRepository = saRepository;
    }

    @Override
    public void updateSystemAdmin(Long id, UpdateSystemAdminRequest request) {
        SystemAdmin systemAdmin = _saRepository.findOneById(id);
        if(request.getFirstName() != null)
            systemAdmin.setFirstName(request.getFirstName());
        if(request.getLastName() != null)
            systemAdmin.setLastName(request.getLastName());

        _saRepository.save(systemAdmin);
    }

    @Override
    public SystemAdminResponse getSystemAdmin(Long id) {
        SystemAdmin systemAdmin = _saRepository.findOneById(id);
        if(systemAdmin != null) {
            return mapSystemAdminToSystemAdminResponse(systemAdmin);
        } else {
            return null;
        }
    }

    private SystemAdminResponse mapSystemAdminToSystemAdminResponse(SystemAdmin systemAdmin) {
        SystemAdminResponse systemAdminResponse = new SystemAdminResponse();
        systemAdminResponse.setId(systemAdmin.getId());
        systemAdminResponse.setFirstName(systemAdmin.getFirstName());
        systemAdminResponse.setLastName(systemAdmin.getLastName());
        systemAdminResponse.setUsername(systemAdmin.getUser().getUsername());
        systemAdminResponse.setUserRole(systemAdmin.getUser().getUserRole().toString());
        return systemAdminResponse;
    }
}
