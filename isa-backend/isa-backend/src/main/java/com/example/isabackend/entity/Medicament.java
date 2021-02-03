package com.example.isabackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Medicament {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "shape")
    private String shape;

    @Column(name = "type")
    private String type;

    @Column(name = "replacement")
    private String replacement;

    @Column(name = "contraindications")
    private String contraindications;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "issuance")
    private String issuance;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "medicament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PharmacyMedicament> pharmacistMedicaments;

    @ManyToMany(mappedBy = "medicaments")
    private Collection<Patient> patients;

    @OneToMany(mappedBy = "medicament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;


}

