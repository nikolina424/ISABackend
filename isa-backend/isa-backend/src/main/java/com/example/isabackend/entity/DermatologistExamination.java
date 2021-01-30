package com.example.isabackend.entity;

import com.example.isabackend.util.enums.ExaminationStatus;
import com.example.isabackend.util.enums.UserRoles;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class DermatologistExamination {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time_examination")
    private LocalTime startTimeExamination;

    @Column(name = "end_time_examination")
    private LocalTime endTimeExamination;

    @Column(name = "date_examination")
    private LocalDate dateExamination;

    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dermatologist_id")
    private Dermatologist dermatologist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    private ExaminationStatus examinationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;


}
