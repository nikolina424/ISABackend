package com.example.isabackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pharmacist {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String firstName;

    private String lastName;

    private String number;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @OneToMany(mappedBy = "pharmacist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShiftPharmacist> pharmacistShifts;

    @OneToMany(mappedBy = "pharmacist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "pharmacist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complaint> complaints;

    @OneToMany(mappedBy = "pharmacist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseOrder> purchaseOrders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pricelist_id")
    private Pricelist pricelist;

    private double price;
}
