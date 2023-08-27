package com.blazejherzog.jobcandidatemanager.candidate.domain.model;

import com.blazejherzog.jobcandidatemanager.shared.api.Company;
import com.blazejherzog.jobcandidatemanager.shared.domain.AdditionalData;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
public class CandidateData extends AdditionalData {


    private Company currentCompany;
    private String currentOccupation;
    private String currentIndustry;
    private double currentOccupationExperience;
    private List<String> skills;
    private List<LanguageProficiency> languageProficiencies;
    private String personalSummary;
    private boolean hasCvUploaded;


}
