package com.example.isabackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "pharmacy_medicament")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyMedicament {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicament_id")
    private Medicament medicament;

    @OneToMany(mappedBy = "pharmacyMedicament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicamentReservation> medicamentReservations;
}
