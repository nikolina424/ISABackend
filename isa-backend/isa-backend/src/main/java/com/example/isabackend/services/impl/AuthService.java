package com.example.isabackend.services.impl;

import com.example.isabackend.dto.request.*;
import com.example.isabackend.dto.response.PharmacyAdminResponse;
import com.example.isabackend.dto.response.UserResponse;
import com.example.isabackend.entity.*;
import com.example.isabackend.repository.*;
import com.example.isabackend.security.TokenUtils;
import com.example.isabackend.services.IAuthService;
import com.example.isabackend.util.GeneralException;
import com.example.isabackend.util.enums.RequestStatus;
import com.example.isabackend.util.enums.UserRoles;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class AuthService implements IAuthService {

    private final TokenUtils _tokenUtils;
    private final IUserRepository _userRepository;
    private final PasswordEncoder _passwordEncoder;
    private final IAuthorityRepository _authorityRepository;
    private final IPatientRepository _patientRepository;
    private final PharmacyAdminService _pharmacyAdminService;
    private final IPharmacistRepository _pharmacistRepository;
    private final IPharmacyRepository _pharmacyRepository;
    private final IShiftPharmacistRepository _shiftPharmacyRepository;
    private final IPharmacyAdminRepository _pharmacyAdminRepository;
    private final ISystemAdminRepository _systemAdminRepository;
    private final IDermatologistRepository _dermatologistRepository;
    private final ISupplierRepository _supplierRepository;
    private final IPricelistRepository _pricelistRepository;

    public AuthService(TokenUtils tokenUtils, IUserRepository userRepository, PasswordEncoder passwordEncoder, IAuthorityRepository authorityRepository, IPatientRepository patientRepository, PharmacyAdminService pharmacyAdminService, IPharmacistRepository pharmacistRepository, IPharmacyRepository pharmacyRepository, IShiftPharmacistRepository shiftPharmacyRepository, IPharmacyAdminRepository pharmacyAdminRepository, ISystemAdminRepository systemAdminRepository, IDermatologistRepository dermatologistRepository, ISupplierRepository supplierRepository, IPricelistRepository pricelistRepository) {
        _tokenUtils = tokenUtils;
        _userRepository = userRepository;
        _passwordEncoder = passwordEncoder;
        _authorityRepository = authorityRepository;
        _patientRepository = patientRepository;
        _pharmacyAdminService = pharmacyAdminService;
        _pharmacistRepository = pharmacistRepository;
        _pharmacyRepository = pharmacyRepository;
        _shiftPharmacyRepository = shiftPharmacyRepository;
        _pharmacyAdminRepository = pharmacyAdminRepository;
        _systemAdminRepository = systemAdminRepository;
        _dermatologistRepository = dermatologistRepository;
        _supplierRepository = supplierRepository;
        _pricelistRepository = pricelistRepository;
    }

    @Override
    public String getPermission(String token) {
        String username = _tokenUtils.getUsernameFromToken(token);
        User user = _userRepository.findOneByUsername(username);
        String retVal = "";
        for (GrantedAuthority authority : user.getAuthorities()) {
            retVal += authority.getAuthority()+",";
        }
        return retVal.substring(0,retVal.length()-1);
    }

    @Override
    public UserResponse login(LoginRequest request) {
        User user = _userRepository.findOneByUsername(request.getUsername());
        System.out.println(user.getUsername());
        if(user == null || !_passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new GeneralException("Bad credentials.", HttpStatus.BAD_REQUEST);
        }
        if(user.getPatient() != null && user.getPatient().getRequestStatus().equals(RequestStatus.PENDING)){
            throw new GeneralException("Your registration hasn't been approved yet.", HttpStatus.BAD_REQUEST);
        }
        if(user.getPatient() != null && user.getPatient().getRequestStatus().equals(RequestStatus.DENIED)){
            throw new GeneralException("Your registration has been denied.", HttpStatus.BAD_REQUEST);
        }
        if(user.getPatient() != null && user.getPatient().getRequestStatus().equals(RequestStatus.APPROVED)){
            throw new GeneralException("Your registration has been approved by admin. Please activate your account.", HttpStatus.BAD_REQUEST);
        }
        if(user.getPatient() != null && user.getPatient().isDeleted()) {
            throw new GeneralException("Your account is blocked by admin.", HttpStatus.BAD_REQUEST);
        }

        UserResponse userResponse = mapUserToUserResponse(user);
        return userResponse;
    }

    @Override
    public UserResponse registerPatient(RegistrationRequest request) {
        if(!request.getPassword().equals(request.getRePassword())){
            throw new GeneralException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        Patient patient = new Patient();
        user.setUsername(request.getUsername());
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        user.setUserRole(UserRoles.PATIENT);
        user.setHasSignedIn(false);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(_authorityRepository.findOneByName("ROLE_PATIENT"));
        user.setAuthorities(new HashSet<>(authorities));
        patient.setDeleted(false);
        patient.setRequestStatus(RequestStatus.PENDING);
        patient.setAddress(request.getAddress());
        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setNumber(request.getNumber());
        patient.setCity(request.getCity());
        patient.setCountry(request.getCountry());

        Patient savedPatient = _patientRepository.save(patient);
        savedPatient.setUser(user);
        user.setPatient(savedPatient);
        User savedUser = _userRepository.save(user);

        return mapUserToUserResponse(savedUser);

    }

    @Override
    public boolean registerPharmacist(PharmacistRequest request) {
        if(!request.getPassword().equals(request.getRePassword())){
            throw new GeneralException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        Pharmacist pharmacist = new Pharmacist();
        ShiftPharmacist shiftPharmacist = new ShiftPharmacist();

        if(request.getUsername().equals(_userRepository.findOneByUsername(request.getUsername()))){
            return false;
        }
        user.setUsername(request.getUsername());
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        user.setUserRole(UserRoles.PHARMACIST);
        user.setHasSignedIn(false);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(_authorityRepository.findOneByName("ROLE_PHARMACIST"));
        user.setAuthorities(new HashSet<>(authorities));

        pharmacist.setFirstName(request.getFirstName());
        pharmacist.setLastName(request.getLastName());
        pharmacist.setNumber(request.getNumber());
        pharmacist.setAddress(request.getAddress());
        pharmacist.setPrice(request.getPrice());
        List<Pricelist> pricelists = _pricelistRepository.getAllByPharmacyId(request.getPharmacyId());
        Pricelist pricelist = new Pricelist();
        for(Pricelist p: pricelists){
            if(p.getActive()){
                pricelist = p;
            }
        }
        pharmacist.setPricelist(pricelist);

        Pharmacy pharmacy = _pharmacyRepository.findOneById(request.getPharmacyId());
        pharmacist.setPharmacy(pharmacy);



        Pharmacist savedPharmacist = _pharmacistRepository.save(pharmacist);
        savedPharmacist.setUser(user);
        user.setPharmacist(savedPharmacist);
        _userRepository.save(user);

        LocalTime startShift = LocalTime.parse(request.getStartShift());
        LocalTime endShift = LocalTime.parse(request.getEndShift());
        shiftPharmacist.setStartShift(startShift.plusHours(1));
        shiftPharmacist.setEndShift(endShift.plusHours(1));
        shiftPharmacist.setPharmacist(savedPharmacist);
        shiftPharmacist.setPharmacy(pharmacy);
        _shiftPharmacyRepository.save(shiftPharmacist);


        return true;
    }

    @Override
    public void changePasswordForPharmacyAdmin(Long id, ChangePasswordRequest request) {
        PharmacyAdmin pharmacyAdmin = _pharmacyAdminRepository.findOneById(id);
        User user = pharmacyAdmin.getUser();
        if(_passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
           if(request.getRePassword().equals(request.getPassword())){
               user.setPassword(_passwordEncoder.encode(request.getPassword()));
               PharmacyAdmin savedPharmacyAdmin = _pharmacyAdminRepository.save(pharmacyAdmin);
               user.setPharmacyAdmin(savedPharmacyAdmin);
               _userRepository.save(user);

           }
        }
    }

    @Override
    public boolean registerPharmacyAdmin(PharmacyAdminRequest request) {
        if(!request.getPassword().equals(request.getRePassword())){
            throw new GeneralException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        PharmacyAdmin pharmacyAdmin = new PharmacyAdmin();

        if(request.getUsername().equals(_userRepository.findOneByUsername(request.getUsername()))){
            return false;
        }
        user.setUsername(request.getUsername());
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        user.setUserRole(UserRoles.PHARMACIST);
        user.setHasSignedIn(false);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(_authorityRepository.findOneByName("ROLE_PHARMACIST"));
        user.setAuthorities(new HashSet<>(authorities));

        pharmacyAdmin.setFirstName(request.getFirstName());
        pharmacyAdmin.setLastName(request.getLastName());
        Pharmacy pharmacy = _pharmacyRepository.findOneById(request.getPharmacyId());
        pharmacyAdmin.setPharmacy(pharmacy);



        PharmacyAdmin savedPharmacyAdmin = _pharmacyAdminRepository.save(pharmacyAdmin);
        savedPharmacyAdmin.setUser(user);
        user.setPharmacyAdmin(savedPharmacyAdmin);
        _userRepository.save(user);

        return true;
    }

    @Override
    public boolean registerSystemAdmin(SystemAdminRequest request) {
        if(!request.getPassword().equals(request.getRePassword())){
            throw new GeneralException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        SystemAdmin systemAdmin = new SystemAdmin();

        if(request.getUsername().equals(_userRepository.findOneByUsername(request.getUsername()))){
            return false;
        }
        user.setUsername(request.getUsername());
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        user.setUserRole(UserRoles.SYSTEM_ADMIN);
        user.setHasSignedIn(false);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(_authorityRepository.findOneByName("ROLE_SYSTEM_ADMIN"));
        user.setAuthorities(new HashSet<>(authorities));

        systemAdmin.setFirstName(request.getFirstName());
        systemAdmin.setLastName(request.getLastName());


        SystemAdmin savedSystemAdmin = _systemAdminRepository.save(systemAdmin);
        savedSystemAdmin.setUser(user);
        user.setSystemAdmin(savedSystemAdmin);
        _userRepository.save(user);

        return true;
    }

    @Override
    public boolean registerDermatologist(DermatologistRequest request) {
        if(!request.getPassword().equals(request.getRePassword())){
            throw new GeneralException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        Dermatologist dermatologist = new Dermatologist();
        if(request.getUsername().equals(_userRepository.findOneByUsername(request.getUsername()))){
            return false;
        }
        user.setUsername(request.getUsername());
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        user.setUserRole(UserRoles.DERMATOLOGIST);
        user.setHasSignedIn(false);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(_authorityRepository.findOneByName("ROLE_DERMATOLOGIST"));
        user.setAuthorities(new HashSet<>(authorities));

        dermatologist.setFirstName(request.getFirstName());
        dermatologist.setLastName(request.getLastName());
        dermatologist.setNumber(request.getNumber());
        dermatologist.setAddress(request.getAddress());

        Dermatologist savedDermatologist = _dermatologistRepository.save(dermatologist);
        savedDermatologist.setUser(user);
        user.setDermatologist(savedDermatologist);
        _userRepository.save(user);

        return true;
    }

    @Override
    public boolean registerSupplier(SupplierRequest request) {
        if(!request.getPassword().equals(request.getRePassword())){
            throw new GeneralException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        Supplier supplier = new Supplier();
        if(request.getUsername().equals(_userRepository.findOneByUsername(request.getUsername()))){
            return false;
        }
        user.setUsername(request.getUsername());
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        user.setUserRole(UserRoles.SUPPLIER);
        user.setHasSignedIn(false);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(_authorityRepository.findOneByName("ROLE_SUPPLIER"));
        user.setAuthorities(new HashSet<>(authorities));

        supplier.setFirstName(request.getFirstName());
        supplier.setLastName(request.getLastName());
        supplier.setNumber(request.getNumber());
        supplier.setAddress(request.getAddress());

        Supplier savedSupplier = _supplierRepository.save(supplier);
        savedSupplier.setUser(user);
        user.setSupplier(savedSupplier);
        _userRepository.save(user);

        return true;
    }

    @Override
    public void changePasswordForPatient(Long id, ChangePasswordRequest request) {
        Patient patient = _patientRepository.findOneById(id);
        User user = patient.getUser();
        if(_passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            if(request.getRePassword().equals(request.getPassword())){
                user.setPassword(_passwordEncoder.encode(request.getPassword()));
                Patient savedPatient = _patientRepository.save(patient);
                user.setPatient(savedPatient);
                _userRepository.save(user);

            }
        }
    }

    @Override
    public void changePasswordForDermatologist(Long id, ChangePasswordRequest request) {
        Dermatologist dermatologist = _dermatologistRepository.findOneById(id);
        User user = dermatologist.getUser();
        if(_passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            if(request.getRePassword().equals(request.getPassword())){
                user.setPassword(_passwordEncoder.encode(request.getPassword()));
                Dermatologist savedDermatologist = _dermatologistRepository.save(dermatologist);
                user.setDermatologist(savedDermatologist);
                _userRepository.save(user);

            }
        }
    }

    @Override
    public void changePasswordForSupplier(Long id, ChangePasswordRequest request) {
        Supplier supplier = _supplierRepository.findOneById(id);
        User user = supplier.getUser();
        if(_passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            if(request.getRePassword().equals(request.getPassword())){
                user.setPassword(_passwordEncoder.encode(request.getPassword()));
                Supplier savedSupplier = _supplierRepository.save(supplier);
                user.setSupplier(savedSupplier);
                _userRepository.save(user);

            }
        }
    }

    @Override
    public void changePasswordForPharmacist(Long id, ChangePasswordRequest request) {
        Pharmacist pharmacist = _pharmacistRepository.findOneById(id);
        User user = pharmacist.getUser();
        if(_passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            if(request.getRePassword().equals(request.getPassword())){
                user.setPassword(_passwordEncoder.encode(request.getPassword()));
                Pharmacist savedPharmacist = _pharmacistRepository.save(pharmacist);
                user.setPharmacist(savedPharmacist);
                _userRepository.save(user);

            }
        }
    }

    @Override
    public void changePasswordForSystemAdmin(Long id, ChangePasswordRequest request) {
        SystemAdmin systemAdmin = _systemAdminRepository.findOneById(id);
        User user = systemAdmin.getUser();
        if(_passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            if(request.getRePassword().equals(request.getPassword())){
                user.setPassword(_passwordEncoder.encode(request.getPassword()));
                SystemAdmin savedAdmin = _systemAdminRepository.save(systemAdmin);
                user.setSystemAdmin(savedAdmin);
                _userRepository.save(user);

            }
        }
    }


    private UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        if(user.getPatient() != null){
            userResponse.setId(user.getPatient().getId());
        }else if(user.getDermatologist() != null){
            userResponse.setId(user.getDermatologist().getId());
        }else if(user.getPharmacist() != null){
            userResponse.setId(user.getPharmacist().getId());
        }else if(user.getSystemAdmin() != null){
            userResponse.setId(user.getSystemAdmin().getId());
        }else if(user.getPharmacyAdmin() != null){
            userResponse.setId(user.getPharmacyAdmin().getId());
        }else if(user.getSupplier() != null){
            System.out.println("Ja sam simple user");
            System.out.println(user.getSupplier().getId());
            System.out.println("Ovo je moj id");
            userResponse.setId(user.getSupplier().getId());
            System.out.println(userResponse.getId());
        }
        userResponse.setUsername(user.getUsername());
        if(user.getRoles().contains(_authorityRepository.findOneByName("ROLE_PATIENT"))){
            userResponse.setUserRole("PATIENT");
        }else if(user.getRoles().contains(_authorityRepository.findOneByName("ROLE_PHARMACIST"))){
            userResponse.setUserRole("PHARMACIST");
            //userResponse.setPharmacyId();
        }else if(user.getRoles().contains(_authorityRepository.findOneByName("ROLE_DERMATOLOGIST"))){
            userResponse.setUserRole("DERMATOLOGIST");
        }else if(user.getRoles().contains(_authorityRepository.findOneByName("ROLE_SYSTEM_ADMIN"))){
            userResponse.setUserRole("SYSTEM_ADMIN");
        }else if(user.getRoles().contains(_authorityRepository.findOneByName("ROLE_PHARMACY_ADMIN"))){
            userResponse.setUserRole("PHARMACY_ADMIN");
            PharmacyAdminResponse pharmacyAdminResponse = _pharmacyAdminService.getPharmacyAdminByUserId(user.getId());
            userResponse.setPharmacyId(pharmacyAdminResponse.getPharmacyId());
        }else if(user.getRoles().contains(_authorityRepository.findOneByName("ROLE_SUPPLIER"))){
            userResponse.setUserRole("SUPPLIER");
        }
        return userResponse;
    }
}
