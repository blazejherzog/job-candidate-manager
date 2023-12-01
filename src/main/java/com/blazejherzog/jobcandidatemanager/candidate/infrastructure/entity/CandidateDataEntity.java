package com.blazejherzog.jobcandidatemanager.candidate.infrastructure.entity;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.UserEntity;
import com.blazejherzog.jobcandidatemanager.shared.infrastructure.AddressEntity;
import com.blazejherzog.jobcandidatemanager.shared.infrastructure.CompanyEntity;
import com.blazejherzog.jobcandidatemanager.shared.infrastructure.LanguageProficiencyEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Table(name = "CANDIDATE_DATA")
public class CandidateDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne
    @JoinColumn(name = "current_company_id")
    private CompanyEntity currentCompany;

    private String currentOccupation;

    private String currentIndustry;

    private double currentOccupationExperience;

    private List<String> skills;

    @OneToMany
    @JoinColumn(name = "language_proficiencies_id")
    private List<LanguageProficiencyEntity> languageProficiencies;

    private String personalSummary;

    private boolean hasCvUploaded;
}
