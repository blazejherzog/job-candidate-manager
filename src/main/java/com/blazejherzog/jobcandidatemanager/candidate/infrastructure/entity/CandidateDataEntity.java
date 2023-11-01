package com.blazejherzog.jobcandidatemanager.candidate.infrastructure.entity;

import com.blazejherzog.jobcandidatemanager.shared.infrastructure.AddressEntity;
import com.blazejherzog.jobcandidatemanager.shared.infrastructure.CompanyEntity;
import com.blazejherzog.jobcandidatemanager.shared.infrastructure.LanguageProficiencyEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class CandidateDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long userId;

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
