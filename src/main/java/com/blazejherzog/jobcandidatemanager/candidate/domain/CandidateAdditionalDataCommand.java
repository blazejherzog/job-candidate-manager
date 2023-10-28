package com.blazejherzog.jobcandidatemanager.candidate.domain;

import com.blazejherzog.jobcandidatemanager.candidate.domain.model.LanguageProficiency;
import com.blazejherzog.jobcandidatemanager.shared.domain.Address;
import com.blazejherzog.jobcandidatemanager.shared.domain.Company;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CandidateAdditionalDataCommand{

    private Long userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address privateAddress;
    private Company currentCompany;
    private String currentOccupation;
    private String currentIndustry;
    private double currentOccupationYearsExperience;
    private List<String> skills;
    private List<LanguageProficiency> languageProficiencies;
    private String personalSummary;
    private boolean hasCvUploaded;

}
