package com.example.isabackend.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class PriceMedicament {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_medicament_id")
    private PharmacyMedicament pharmacyMedicament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pricelist_id")
    private Pricelist pricelist;
}
