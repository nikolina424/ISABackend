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

    @Column(name = "type")
    private String type;

    @Column(name = "contraindications")
    private String contraindications;

    @Column(name = "ingredients")
    private String ingredients;

    @OneToMany(mappedBy = "medicament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PharmacyMedicament> pharmacistMedicaments;

    @ManyToMany(mappedBy = "medicaments")
    private Collection<Patient> patients;
}

