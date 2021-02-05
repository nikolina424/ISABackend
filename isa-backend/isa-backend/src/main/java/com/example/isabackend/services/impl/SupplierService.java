package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.UpdateSupplierRequest;
import com.example.isabackend.dto.response.PatientResponse;
import com.example.isabackend.dto.response.SupplierResponse;
import com.example.isabackend.entity.Dermatologist;
import com.example.isabackend.entity.Patient;
import com.example.isabackend.entity.Supplier;
import com.example.isabackend.repository.ISupplierRepository;
import com.example.isabackend.services.ISupplierService;
import org.springframework.stereotype.Service;

@Service
public class SupplierService implements ISupplierService {
    private final ISupplierRepository _supplierRepository;

    public SupplierService(ISupplierRepository supplierRepository) {
        _supplierRepository = supplierRepository;
    }

    @Override
    public void updateSupplier(Long id, UpdateSupplierRequest request) {
        Supplier supplier = _supplierRepository.findOneById(id);
        if(request.getAddress() != null)
            supplier.setAddress(request.getAddress());
        if(request.getFirstName() != null)
            supplier.setFirstName(request.getFirstName());
        if(request.getLastName() != null)
            supplier.setLastName(request.getLastName());
        if(request.getNumber() != null)
            supplier.setNumber(request.getNumber());


        _supplierRepository.save(supplier);
    }

    @Override
    public SupplierResponse getSupplier(Long id) {
        Supplier supplier = _supplierRepository.findOneById(id);
        if(supplier != null) {
            return mapSupplierToSupplierResponse(supplier);
        } else {
            return null;
        }
    }

    public SupplierResponse mapSupplierToSupplierResponse(Supplier supplier) {
        SupplierResponse supplierResponse = new SupplierResponse();
        supplierResponse.setId(supplier.getId());
        supplierResponse.setAddress(supplier.getAddress());
        supplierResponse.setFirstName(supplier.getFirstName());
        supplierResponse.setLastName(supplier.getLastName());
        supplierResponse.setUsername(supplier.getUser().getUsername());
        supplierResponse.setNumber(supplier.getNumber());
        supplierResponse.setUserRole(supplier.getUser().getUserRole().toString());
        return supplierResponse;
    }
}
