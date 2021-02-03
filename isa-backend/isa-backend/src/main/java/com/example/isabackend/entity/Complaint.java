package com.example.isabackend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Complaint {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dermatologist_id")
    private Dermatologist dermatologist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacist_id")
    private Pharmacist pharmacist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private boolean answered;
}
