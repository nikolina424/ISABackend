package com.example.isabackend.services.impl;

import com.example.isabackend.config.EmailContext;
import com.example.isabackend.dto.request.AnswerComplaintRequest;
import com.example.isabackend.entity.DermatologistExamination;
import com.example.isabackend.entity.MedicamentReservation;
import com.example.isabackend.entity.Patient;
import com.example.isabackend.repository.IPatientRepository;
import com.example.isabackend.services.IEmailService;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class EmailService implements IEmailService {

    private final EmailContext _emailContext;
    private final IPatientRepository _patientRepository;

    public EmailService(EmailContext emailContext, IPatientRepository patientRepository) {
        _emailContext = emailContext;
        _patientRepository = patientRepository;
    }

    @Override
    public void approveRegistrationMail(Patient patient) {
        String to = patient.getUser().getUsername();
        System.out.println(to);
        String subject = "Your registration has been approved.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", patient.getFirstName(), patient.getLastName()));
        context.setVariable("link", String.format("http://localhost:4200/frontpage/login/%s", patient.getId()));
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

    public void approveMedicamentReservation(MedicamentReservation savedReservation) {
        String to = savedReservation.getPatient().getUser().getUsername();
        System.out.println(to);
        String subject = "Your medicament reservation has been approved.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", savedReservation.getPatient().getFirstName(), savedReservation.getPatient().getLastName()));
        context.setVariable("reservationId", String.format("%s", savedReservation.getId()));
        _emailContext.send(to, subject, "approvedMedicamentReservation", context);
    }

    public void approveDermatologistExaminationReservation(DermatologistExamination savedReservation) {
        String to = savedReservation.getPatient().getUser().getUsername();
        System.out.println(to);
        String subject = "Your dermatologist examination reservation has been approved.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", savedReservation.getPatient().getFirstName(), savedReservation.getPatient().getLastName()));
        _emailContext.send(to, subject, "approveDermatologistExaminationReservation", context);
    }

    public void answerOnComplaint(AnswerComplaintRequest request) {
        Patient patient = _patientRepository.findOneById(request.getPatientId());
        String to = patient.getUser().getUsername();
        String subject = "Answer on complaint";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", patient.getFirstName(), patient.getLastName()));
        context.setVariable("text", String.format("%s",  request.getText()));
        _emailContext.send(to, subject, "answerOnComplaint", context);
    }
}
