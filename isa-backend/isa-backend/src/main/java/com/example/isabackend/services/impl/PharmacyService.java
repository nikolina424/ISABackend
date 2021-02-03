package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.PharmacyRequest;
import com.example.isabackend.dto.request.RemoveFromPharmacyRequest;
import com.example.isabackend.dto.request.UpdatePharmacyRequest;
import com.example.isabackend.dto.response.*;
import com.example.isabackend.entity.*;
import com.example.isabackend.repository.*;
import com.example.isabackend.services.IPharmacyService;
import com.example.isabackend.util.enums.MedicamentReservationStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacyService implements IPharmacyService {
    private final IPharmacyRepository _pharmacyRepository;
    private final IMedicamentRepository _medicamentRepository;
    private final IPharmacyMedicamentRepository _phRepository;
    private final IMedicamentReservationRepository _mrRepository;
    private final IDermatologistRepository _dermatologistRepository;
    private final IDermatologistExaminationRepository _deRepository;
    private final IShiftRepository _shiftRepository;
    private final ShiftService _shiftService;
    private final IShiftPharmacistRepository _spRepository;
    private final IPharmacistExaminationRepository _peRepository;

    public PharmacyService(IPharmacyRepository pharmacyRepository, IMedicamentRepository medicamentRepository, IPharmacyMedicamentRepository phRepository, IMedicamentReservationRepository mrRepository, IDermatologistRepository dermatologistRepository, IDermatologistExaminationRepository deRepository, IShiftRepository shiftRepository, ShiftService shiftService, IShiftPharmacistRepository spRepository, IPharmacistExaminationRepository peRepository) {
        _pharmacyRepository = pharmacyRepository;
        _medicamentRepository = medicamentRepository;
        _phRepository = phRepository;
        _mrRepository = mrRepository;
        _dermatologistRepository = dermatologistRepository;
        _deRepository = deRepository;
        _shiftRepository = shiftRepository;
        _shiftService = shiftService;
        _spRepository = spRepository;
        _peRepository = peRepository;
    }

    @Override
    public void updatePharmacy(Long id, UpdatePharmacyRequest request) {
        Pharmacy pharmacy = _pharmacyRepository.findOneById(id);
        if(request.getAddress() != null)
            pharmacy.setAddress(request.getAddress());
        if(request.getName() != null)
            pharmacy.setName(request.getName());
        if(request.getAbout() != null)
            pharmacy.setAbout(request.getAbout());
        _pharmacyRepository.save(pharmacy);
    }

    @Override
    public PharmacyResponse getPharmacyById(Long id) {
        Pharmacy pharmacy = _pharmacyRepository.findOneById(id);
        if(pharmacy != null) {
            return mapPharmacyToPharmacyResponse(pharmacy);
        } else {
            return null;
        }
    }

    @Override
    public List<PharmacyResponse> getAllPharmacies() {
        List<Pharmacy> pharmacies = _pharmacyRepository.findAll();
        List<PharmacyResponse> pharmacyResponses = new ArrayList<>();
        for (Pharmacy pharmacy: pharmacies) {
            PharmacyResponse pharmacyResponse = mapPharmacyToPharmacyResponse(pharmacy);
            pharmacyResponses.add(pharmacyResponse);
        }
        return pharmacyResponses;
    }

    @Override
    public boolean registerPharmacy(PharmacyRequest request) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName(request.getName());
        pharmacy.setAbout(request.getAbout());
        pharmacy.setAddress(request.getAddress());
        _pharmacyRepository.save(pharmacy);
        return true;
    }

    @Override
    public SearchPharmacyResponse searchPharmacies(String name) {
        List<Pharmacy> filteredPharmacies = filteredPharmacies(name);
        List<PharmacyResponse> pharmacyResponses =  mapPharmacyListToPharmacyResponseList(filteredPharmacies);
        return mapToSearchResponse(pharmacyResponses);
    }

    @Override
    public List<PharmacyResponse> getPharmaciesByMedicamentId(Long id) {
        Medicament medicament = _medicamentRepository.findOneById(id);
        List<PharmacyMedicament> pharmacyMedicaments = _phRepository.findAllByMedicament_Id(medicament.getId());
        List<Pharmacy> pharmacies = new ArrayList<>();
        for(PharmacyMedicament pharmacyMedicament: pharmacyMedicaments){
            Pharmacy pharmacy = pharmacyMedicament.getPharmacy();
            pharmacies.add(pharmacy);
        }
        return mapPharmacyListToPharmacyResponseList(pharmacies);
    }

    @Override
    public boolean removeMedicament(RemoveFromPharmacyRequest request) {
        PharmacyMedicament pharmacyMedicament = _phRepository.findOneById(request.getItemId());
        List<MedicamentReservation> medicamentReservations = _mrRepository.findAllByPharmacyMedicament_Id(pharmacyMedicament.getId());
        for (MedicamentReservation mr: medicamentReservations) {
            if(mr.getMedicamentReservationStatus() == MedicamentReservationStatus.RESERVED){
                return false;
            }

        }
        Pharmacy pharmacy = _pharmacyRepository.findOneById(request.getPharmacyId());
        List<PharmacyMedicament> pharmacyMedicaments =  pharmacy.getPharmacistMedicaments();
        pharmacyMedicaments.remove(pharmacyMedicament);
        pharmacy.setPharmacistMedicaments(pharmacyMedicaments);
        _pharmacyRepository.save(pharmacy);
        return true;
    }

    @Override
    public boolean removePharmacist(RemoveFromPharmacyRequest request) {
        return false;
    }

    @Override
    public boolean removeDermatologist(RemoveFromPharmacyRequest request) { return false; }

    @Override
    public List<PharmacyResponse> getPharmaciesByDate(String dateExamination, String startExamination, String endExamination) {
        LocalTime start = LocalTime.parse(startExamination);
        LocalTime end = LocalTime.parse(endExamination);
        LocalDate date = LocalDate.parse(dateExamination);

        List<Pharmacy> pharmacies = new ArrayList<>();
        List<Pharmacy> finalPharmacies = new ArrayList<>();

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
                    System.out.println("Pocetak smene:");
                    System.out.println(shiftPharmacist.getStartShift());
                    System.out.println("Je pre pocetka upita");
                    System.out.println(start);
                    System.out.println("Kraj smene:");
                    System.out.println(shiftPharmacist.getEndShift());
                    System.out.println("Je posle kraja upita");


                    System.out.println("Ovo je smena koja odgovara ovom zahtevu i ubacujem je u listu:");
                    System.out.println(shiftPharmacist.getId());
                    //System.out.println(shiftPharmacist.getPharmacy().getId());
                    //System.out.println("ovo gore su apoteke koje mogu imati slobodan termin");
                    //System.out.println(shiftPharmacist.getId());
                    System.out.println("Ovo je apoteka koja odgovara i ubacujem je");
                    System.out.println(shiftPharmacist.getPharmacy().getName());
                    pharmacies.add(shiftPharmacist.getPharmacy());
                }else if(shiftPharmacist.getEndShift().equals(end)){
                    list.add(shiftPharmacist);
                    pharmacies.add(shiftPharmacist.getPharmacy());
                }
            }else if(shiftPharmacist.getStartShift().equals(start)){
                list.add(shiftPharmacist);
                pharmacies.add(shiftPharmacist.getPharmacy());
            }
        }

        for(ShiftPharmacist shiftPharmacist: list){
            for(PharmacistExamination pharmacistExamination: pharmacistExaminations){
                if(shiftPharmacist == pharmacistExamination.getShiftPharmacist()){
                    if(!newList.contains(pharmacistExamination)){
                        newList.add(pharmacistExamination);
                        System.out.println("Ovaj pregled je u redu i ubacujem ga u nasu narednu listu:");
                        System.out.println(pharmacistExamination.getId());

                    }

                }
            }
        }

        for(PharmacistExamination pharmacistExamination: newList){
            System.out.println("Prolazim kroz pregled farmaceuta broj:");
            System.out.println(pharmacistExamination.getId());
            System.out.println("Uporedjujem njegov datum: ");
            System.out.println(pharmacistExamination.getDateExamination());
            System.out.println("I ovaj poslat");
            System.out.println(date);
            if(pharmacistExamination.getDateExamination().equals(date)){
                System.out.println("Isti sam dan");
                if(start.isBefore(pharmacistExamination.getStartTimeExamination())){
                    if(end.isBefore(pharmacistExamination.getStartTimeExamination())){
                        System.out.println("Pocetak i kraj nove su pre pocetka doktorove zakazane i to je ok");
                        finalList.add(pharmacistExamination);
                    }else{
                        System.out.println("Preklapam seeeeeee");
                        finalPharmacies.add(pharmacistExamination.getShiftPharmacist().getPharmacy());
                        System.out.println("******************");
                        System.out.println(pharmacistExamination.getShiftPharmacist().getPharmacy().getName());
                    }
                }else if(end.isAfter(pharmacistExamination.getEndTimeExamination())){
                    System.out.println("Ovde je posle naseg zakazanog tako da je i to ok");
                    finalList.add(pharmacistExamination);


                }else{
                    System.out.println("Preklapam seeeeeee");
                    finalPharmacies.add(pharmacistExamination.getShiftPharmacist().getPharmacy());
                    System.out.println("******************");
                    System.out.println(pharmacistExamination.getShiftPharmacist().getPharmacy().getName());
                }
            }else{
                System.out.println("nisam isti dan i hocu i ja da ucestvujem");
                finalList.add(pharmacistExamination);
            }
        }
        for(Pharmacy pharmacy:pharmacies){
            System.out.println("Ova apoteka ima sansu");
            System.out.println(pharmacy.getName());
            for(Pharmacy pharmacy1: finalPharmacies){
                System.out.println("Ovu uklanjamo");
                System.out.println(pharmacy1.getName());
                if(pharmacy.getId() == pharmacy1.getId()){
                    pharmacies.remove(pharmacy);
                }
            }
        }
        //list su sve smene koje obuhvataju vreme koje je trazeno
        return mapPharmacyListToPharmacyResponseList(pharmacies);
    }


    private SearchPharmacyResponse mapToSearchResponse(List<PharmacyResponse> pharmacyResponses) {
        SearchPharmacyResponse searchResponse = new SearchPharmacyResponse();
        searchResponse.setPharmacyResponses(pharmacyResponses);
        return searchResponse;
    }

    private List<PharmacyResponse> mapPharmacyListToPharmacyResponseList(List<Pharmacy> pharmacies) {
        List<PharmacyResponse> pharmacyResponses = new ArrayList<>();
        for (Pharmacy p:pharmacies) {
            pharmacyResponses.add(mapPharmacyToPharmacyResponse(p));
        }
        return  pharmacyResponses;
    }

    private List<Pharmacy> filteredPharmacies(String name) {
        List<Pharmacy> allPharmacies = _pharmacyRepository.findAll();
        return allPharmacies
                .stream()
                .filter(pharmacy -> {
                    if(name != "") {
                        return pharmacy.getName().equals(name);
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

    public PharmacyResponse mapPharmacyToPharmacyResponse(Pharmacy pharmacy) {
        PharmacyResponse pharmacyResponse = new PharmacyResponse();
        pharmacyResponse.setId(pharmacy.getId());
        pharmacyResponse.setAddress(pharmacy.getAddress());
        pharmacyResponse.setAbout(pharmacy.getAbout());
        pharmacyResponse.setName(pharmacy.getName());
        float average = 0;
        float sum = 0;
        float counter = 0;
        List<Rating> ratings = pharmacy.getRatings();
        if(ratings.isEmpty()){
            average = 5;
        }else{
            for(Rating r: ratings){
                sum += r.getGrade();
                counter++;
            }
            average = sum/counter;

        }
        pharmacyResponse.setRating(average);
        return pharmacyResponse;
    }

}
