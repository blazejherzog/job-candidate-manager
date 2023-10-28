package com.blazejherzog.jobcandidatemanager.employer.infrastructure.entity;

import com.blazejherzog.jobcandidatemanager.shared.infrastructure.AddressEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EmployerDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long userId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "private_address_id")
    private AddressEntity privateAddress;

    private boolean isHiring;
}
