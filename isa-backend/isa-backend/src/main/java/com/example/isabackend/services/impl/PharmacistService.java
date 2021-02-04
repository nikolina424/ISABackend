package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.UpdatePharmacistRequest;
import com.example.isabackend.dto.response.*;
import com.example.isabackend.entity.*;
import com.example.isabackend.repository.IPharmacistExaminationRepository;
import com.example.isabackend.repository.IPharmacistRepository;
import com.example.isabackend.repository.IShiftPharmacistRepository;
import com.example.isabackend.services.IPharmacistService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacistService implements IPharmacistService {

    private final IPharmacistRepository _pharmacistRepository;
    private final IShiftPharmacistRepository _spRepository;
    private final IPharmacistExaminationRepository _peRepository;

    public PharmacistService(IPharmacistRepository pharmacistRepository, IShiftPharmacistRepository spRepository, IPharmacistExaminationRepository peRepository) {
        _pharmacistRepository = pharmacistRepository;
        _spRepository = spRepository;
        _peRepository = peRepository;
    }

    @Override
    public SearchPharmacistResponse searchPharmacist(String firstName, String lastName) {
        List<Pharmacist> filteredPharmacist = filteredPharmacist(firstName, lastName);
        List<PharmacistResponse> pharmacistResponses =  mapPharmacistsToPharmacistResponses(filteredPharmacist);
        return mapToSearchResponse(pharmacistResponses);
    }

    @Override
    public List<PharmacistResponse> getAllPharmacistByPharmacyId(Long id) {
        List<Pharmacist> allPharmacists = _pharmacistRepository.findAll();
        List<PharmacistResponse> pharmacyPharmacist = new ArrayList<>();
        for (Pharmacist pharmacist: allPharmacists) {
            if(pharmacist.getPharmacy().getId().equals(id)){
                pharmacyPharmacist.add(mapPharmacistToPharmacistResponse(pharmacist));
            }
        }
        return pharmacyPharmacist;
    }

    @Override
    public void updatePharmacist(Long id, UpdatePharmacistRequest request) {
        Pharmacist pharmacist = _pharmacistRepository.findOneById(id);
        if(request.getAddress() != null)
            pharmacist.setAddress(request.getAddress());
        if(request.getFirstName() != null)
            pharmacist.setFirstName(request.getFirstName());
        if(request.getLastName() != null)
            pharmacist.setLastName(request.getLastName());
        if(request.getNumber() != null)
            pharmacist.setNumber(request.getNumber());


        _pharmacistRepository.save(pharmacist);
    }

    @Override
    public PharmacistResponse getPharmacist(Long id) {
        Pharmacist pharmacist = _pharmacistRepository.findOneById(id);
        if(pharmacist != null) {
            return mapPharmacistToPharmacistResponse(pharmacist);
        } else {
            return null;
        }
    }

    @Override
    public List<PharmacistResponse> getPharmacistsDate(String dateExamination, String startExamination, String endExamination, Long pharmacyId) {
        LocalTime start = LocalTime.parse(startExamination);
        LocalTime end = LocalTime.parse(endExamination);
        LocalDate date = LocalDate.parse(dateExamination);

        List<Pharmacist> pharmacists = new ArrayList<>();
        List<Pharmacist> finalPharmacists = new ArrayList<>();

        List<ShiftPharmacist> shiftPharmacists = _spRepository.findAll();
        List<ShiftPharmacist> list = new ArrayList<>();

        List<PharmacistExamination> pharmacistExaminations = _peRepository.findAll();
        List<PharmacistExamination> newList = new ArrayList<>();
        List<PharmacistExamination> finalList = new ArrayList<>();


        //uzeli smo sve smene koje  postoje
        for(ShiftPharmacist shiftPharmacist: shiftPharmacists){
            if(shiftPharmacist.getStartShift().isBefore(start)){
                if(shiftPharmacist.getEndShift().isAfter(end)){
                    list.add(shiftPharmacist);
                    pharmacists.add(shiftPharmacist.getPharmacist());
                }else if(shiftPharmacist.getEndShift().equals(end)){
                    list.add(shiftPharmacist);
                    pharmacists.add(shiftPharmacist.getPharmacist());
                }
            }else if(shiftPharmacist.getStartShift().equals(start)){
                list.add(shiftPharmacist);
                pharmacists.add(shiftPharmacist.getPharmacist());
            }
        }

        for(Pharmacist p: pharmacists){
            System.out.println(p.getFirstName());
        }

        for(ShiftPharmacist shiftPharmacist: list){
            for(PharmacistExamination pharmacistExamination: pharmacistExaminations){
                if(shiftPharmacist == pharmacistExamination.getShiftPharmacist()){
                    if(!newList.contains(pharmacistExamination)){
                        newList.add(pharmacistExamination);

                    }

                }
            }
        }

        for(PharmacistExamination pharmacistExamination: newList){
            if(pharmacistExamination.getDateExamination().equals(date)){
                if(start.isBefore(pharmacistExamination.getStartTimeExamination())){
                    if(end.isBefore(pharmacistExamination.getStartTimeExamination())){
                        finalList.add(pharmacistExamination);
                        System.out.println("Ne izbacujem nikoga");
                    }else{
                        finalPharmacists.add(pharmacistExamination.getShiftPharmacist().getPharmacist());
                        System.out.println("Izbacujem");
                        System.out.println(pharmacistExamination.getShiftPharmacist().getPharmacist().getFirstName());
                    }
                }else if(end.isAfter(pharmacistExamination.getEndTimeExamination())){
                    finalList.add(pharmacistExamination);
                    System.out.println("Ne izbacujem nikoga");

                }else{
                    finalPharmacists.add(pharmacistExamination.getShiftPharmacist().getPharmacist());
                    System.out.println("Izbacujem");
                    System.out.println(pharmacistExamination.getShiftPharmacist().getPharmacist().getFirstName());
                }
            }else{
                finalList.add(pharmacistExamination);
                System.out.println("Ne izbacujem nikoga");
            }
        }

        for(Pharmacist pharmacist:pharmacists){
            if(!finalPharmacists.isEmpty()){
                for(Pharmacist pharmacist1: finalPharmacists){
                    if(pharmacist.getId() == pharmacist1.getId()){
                        pharmacists.remove(pharmacist);
                    }
                }
            }else{
                System.out.println("empti je");
            }
        }
        List<Pharmacist> ff = new ArrayList<>();

        for(Pharmacist pharmacist: pharmacists){
            System.out.println(pharmacist.getFirstName());
            System.out.println(pharmacyId);
            if(pharmacist.getPharmacy().getId() == pharmacyId){
                ff.add(pharmacist);
            }
        }

        return mapPharmacistsToPharmacistResponses(ff);
    }


    private SearchPharmacistResponse mapToSearchResponse(List<PharmacistResponse> pharmacistResponses) {
        SearchPharmacistResponse searchResponse = new SearchPharmacistResponse();
        searchResponse.setPharmacistResponses(pharmacistResponses);
        return searchResponse;
    }

    public List<PharmacistResponse> mapPharmacistsToPharmacistResponses(List<Pharmacist> pharmacists) {
        List<PharmacistResponse> pharmacistResponses = new ArrayList<>();
        for(Pharmacist pharmacist: pharmacists){
            PharmacistResponse response = mapPharmacistToPharmacistResponse(pharmacist);
            pharmacistResponses.add(response);
        }
        return pharmacistResponses;
    }

    public PharmacistResponse mapPharmacistToPharmacistResponse(Pharmacist pharmacist) {
        PharmacistResponse pharmacistResponse = new PharmacistResponse();
        pharmacistResponse.setId(pharmacist.getId());
        pharmacistResponse.setAddress(pharmacist.getAddress());
        pharmacistResponse.setFirstName(pharmacist.getFirstName());
        pharmacistResponse.setLastName(pharmacist.getLastName());
        pharmacistResponse.setUsername(pharmacist.getUser().getUsername());
        pharmacistResponse.setNumber(pharmacist.getNumber());

        float average = 0;
        float sum = 0;
        float counter = 0;
        List<Rating> ratings = pharmacist.getRatings();
        if(ratings.isEmpty()){
            average = 5;
        }else{
            for(Rating r: ratings){
                sum += r.getGrade();
                counter++;
            }
            average = sum/counter;

        }
        pharmacistResponse.setRating(average);

        return pharmacistResponse;
    }

    private List<Pharmacist> filteredPharmacist(String firstName, String lastName) {
        List<Pharmacist> allPharmacists = _pharmacistRepository.findAll();
        return allPharmacists
                .stream()
                .filter(pharmacist -> {
                    if(firstName != "") {
                        return pharmacist.getFirstName().equals(firstName);
                    } else {
                        return true;
                    }
                })
                .filter(pharmacist -> {
                    if(lastName != "") {
                        return pharmacist.getLastName().equals(lastName);
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

}
