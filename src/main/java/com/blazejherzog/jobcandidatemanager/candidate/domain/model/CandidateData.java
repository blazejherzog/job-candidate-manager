package com.blazejherzog.jobcandidatemanager.candidate.domain.model;

import com.blazejherzog.jobcandidatemanager.shared.domain.Address;
import com.blazejherzog.jobcandidatemanager.shared.domain.Company;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CandidateData {

    private Long userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String privateAddress;
    private String currentCompanyName;
    private String currentOccupation;
    private String currentIndustry;
    private double currentOccupationYearsExperience;
    private List<String> skills;
    private List<LanguageProficiency> languageProficiencies;
    private String personalSummary;
    private boolean hasCvUploaded;


}
