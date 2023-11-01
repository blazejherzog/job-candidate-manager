package com.blazejherzog.jobcandidatemanager.shared.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LanguageProficiencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private Long userId;

    private String language;

    private String proficiencyLevel;
}
