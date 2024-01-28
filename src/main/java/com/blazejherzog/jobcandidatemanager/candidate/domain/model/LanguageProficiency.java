package com.blazejherzog.jobcandidatemanager.candidate.domain.model;

import lombok.Value;

@Value
public class LanguageProficiency {

    private String language;
    private ProficiencyLevel proficiencyLevel;
}
