package com.blazejherzog.jobcandidatemanager.shared.infrastructure;

import com.blazejherzog.jobcandidatemanager.candidate.domain.model.ProficiencyLevel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LanguageProficiencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private Long userId;

    private String language;

    private ProficiencyLevel proficiencyLevel;
}
