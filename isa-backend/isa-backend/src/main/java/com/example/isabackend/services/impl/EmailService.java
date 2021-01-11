package com.example.isabackend.services.impl;

import com.example.isabackend.config.EmailContext;
import com.example.isabackend.entity.Patient;
import com.example.isabackend.services.IEmailService;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class EmailService implements IEmailService {

    private final EmailContext _emailContext;

    public EmailService(EmailContext emailContext) {
        _emailContext = emailContext;
    }

    @Override
    public void approveRegistrationMail(Patient patient) {
        String to = patient.getUser().getUsername();
        System.out.println(to);
        String subject = "Your registration has been approved.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", patient.getFirstName(), patient.getLastName()));
        context.setVariable("link", String.format("http://localhost:4200/login/%s", patient.getId()));
        _emailContext.send(to, subject, "approvedRegistration", context);
    }

    @Override
    public void denyRegistrationMail(Patient patient) {
        String to = patient.getUser().getUsername();
        System.out.println(to);
        String subject = "Your registration has been denied.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", patient.getFirstName(), patient.getLastName()));
        _emailContext.send(to, subject, "deniedRegistration", context);
    }
}
