package com.blazejherzog.jobcandidatemanager.employer.infrastructure.entity;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.UserEntity;
import com.blazejherzog.jobcandidatemanager.shared.infrastructure.AddressEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Table(name = "EMPLOYER_DATA")
public class EmployerDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "private_address_id")
    private AddressEntity privateAddress;

    private boolean isHiring;
}
