package com.example.isabackend.services;

import com.example.isabackend.entity.Patient;

public interface IEmailService {
    void approveRegistrationMail(Patient patient);
    void denyRegistrationMail(Patient patient);
}
