package com.example.isabackend.services;

import com.example.isabackend.dto.request.UpdateSystemAdminRequest;
import com.example.isabackend.dto.response.SystemAdminResponse;

public interface ISystemAdminService {
    void updateSystemAdmin(Long id, UpdateSystemAdminRequest request);

    SystemAdminResponse getSystemAdmin(Long id);
}
