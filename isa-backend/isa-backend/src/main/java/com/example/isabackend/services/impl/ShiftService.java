package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.ShiftRequest;
import com.example.isabackend.dto.response.PharmacyResponse;
import com.example.isabackend.dto.response.ShiftResponse;
import com.example.isabackend.entity.Dermatologist;
import com.example.isabackend.entity.Pharmacy;
import com.example.isabackend.entity.Shift;
import com.example.isabackend.repository.IDermatologistRepository;
import com.example.isabackend.repository.IPharmacyRepository;
import com.example.isabackend.repository.IShiftRepository;
import com.example.isabackend.services.IShiftService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftService implements IShiftService {

    private final IShiftRepository _shiftRepository;
    private final IDermatologistRepository _dermatologistRepository;
    private final IPharmacyRepository _pharmacyRepository;

    public ShiftService(IShiftRepository shiftRepository, IDermatologistRepository dermatologistRepository, IPharmacyRepository pharmacyRepository) {
        _shiftRepository = shiftRepository;
        _dermatologistRepository = dermatologistRepository;
        _pharmacyRepository = pharmacyRepository;
    }

    @Override
    public List<ShiftResponse> getAllShiftByDermatologistId(Long id) {
        List<Shift> allShifts = _shiftRepository.findAll();
        List<ShiftResponse> shiftByDermatologist = new ArrayList<>();
        for (Shift shift: allShifts) {
            if(shift.getDermatologist().getId() == id){
                shiftByDermatologist.add(mapShiftToShiftResponse(shift));
            }
        }
        return shiftByDermatologist;
    }

    @Override
    public List<ShiftResponse> getAllShifts() {
        List<Shift> shifts = _shiftRepository.findAll();
        List<ShiftResponse> shiftResponses = new ArrayList<>();
        for (Shift shift: shifts) {
            ShiftResponse shiftResponse = mapShiftToShiftResponse(shift);
            shiftResponses.add(shiftResponse);
        }
        return shiftResponses;
    }

    @Override
    public ShiftResponse createShift(ShiftRequest shiftRequest) {
        List<ShiftResponse> allShifts = mapShiftsToShiftResponses(_shiftRepository.findAll());
        List<ShiftResponse> myShifts = new ArrayList<>();

        LocalTime startShift = LocalTime.parse(shiftRequest.getStartShift());
        LocalTime endShift = LocalTime.parse(shiftRequest.getEndShift());
        startShift = startShift.plusHours(1);
        endShift = endShift.plusHours(1);
        int counter = 0;
        for(ShiftResponse shiftResponse: allShifts){
            if(shiftResponse.getDermatologistId() == shiftRequest.getDermatologistId()){
                myShifts.add(shiftResponse);
            }
        }
        for(ShiftResponse shiftResponse: myShifts){
            if(endShift.isBefore(shiftResponse.getStartShift())){
                //dobro je
            }else if(startShift.isAfter(shiftResponse.getEndShift())){
                //ok je
            }else{
                counter++;
            }
        }
        if(counter == 0){
            Shift shift = createShiftEntity(shiftRequest);
            return mapShiftToShiftResponse(_shiftRepository.save(shift));
        }else{
            return null;
        }


    }

    @Override
    public List<ShiftResponse> getAllShiftsByPharmacyId(Long id) {
        List<Shift> allShifts = _shiftRepository.findAll();
        List<ShiftResponse> shiftByPharmacy = new ArrayList<>();
        for (Shift shift: allShifts) {
            if(shift.getPharmacy().getId() == id){
                shiftByPharmacy.add(mapShiftToShiftResponse(shift));
            }
        }
        return shiftByPharmacy;
    }

    private Shift createShiftEntity(ShiftRequest shiftRequest) {
        LocalTime startShift = LocalTime.parse(shiftRequest.getStartShift());
        LocalTime endShift = LocalTime.parse(shiftRequest.getEndShift());
        Shift shift = new Shift();
        shift.setStartShift(startShift.plusHours(1));
        shift.setEndShift(endShift.plusHours(1));
        Dermatologist dermatologist = _dermatologistRepository.findOneById(shiftRequest.getDermatologistId());
        shift.setDermatologist(dermatologist);

        Pharmacy pharmacy = _pharmacyRepository.findOneById(shiftRequest.getPharmacyId());
        shift.setPharmacy(pharmacy);
        return shift;
    }

    private List<ShiftResponse> mapShiftsToShiftResponses(List<Shift> shifts) {
        List<ShiftResponse> shiftResponses = new ArrayList<>();
        for(Shift shift: shifts){
            ShiftResponse response = mapShiftToShiftResponse(shift);
            shiftResponses.add(response);
        }
        return shiftResponses;
    }

    private ShiftResponse mapShiftToShiftResponse(Shift shift) {
        ShiftResponse shiftResponse = new ShiftResponse();
        shiftResponse.setId(shift.getId());
        shiftResponse.setStartShift(shift.getStartShift());
        shiftResponse.setEndShift(shift.getEndShift());
        shiftResponse.setDermatologistId(shift.getDermatologist().getId());
        shiftResponse.setPharmacyId(shift.getPharmacy().getId());
        return shiftResponse;
    }
}
