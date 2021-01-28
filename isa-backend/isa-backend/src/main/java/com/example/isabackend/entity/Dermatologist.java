package com.example.isabackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Dermatologist {
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

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "pharmacy_id")
    //private Pharmacy pharmacy;

    @OneToMany(mappedBy = "dermatologist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shift> dermatologistShifts;
}
