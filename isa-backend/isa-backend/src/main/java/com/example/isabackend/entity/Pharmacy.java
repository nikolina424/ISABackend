package com.example.isabackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacy {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PharmacyAdmin pharmacyAdmin;

    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pharmacist> pharmacists;

    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Dermatologist> dermatologists;

    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PharmacyMedicament> pharmacistMedicaments;

}