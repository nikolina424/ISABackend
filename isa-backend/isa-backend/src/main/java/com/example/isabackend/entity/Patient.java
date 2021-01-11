package com.example.isabackend.entity;

import com.example.isabackend.util.enums.RequestStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {
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

    private String city;

    private String country;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    private boolean deleted;




}
