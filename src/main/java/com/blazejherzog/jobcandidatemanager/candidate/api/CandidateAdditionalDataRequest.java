package com.blazejherzog.jobcandidatemanager.candidate.api;

import com.blazejherzog.jobcandidatemanager.candidate.domain.model.LanguageProficiency;
import com.blazejherzog.jobcandidatemanager.shared.domain.Address;
import com.blazejherzog.jobcandidatemanager.shared.domain.Company;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CandidateAdditionalDataRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 40)
    private String lastName;

    @NotBlank
    @Size(min = 9, max = 13)
    private String phoneNumber;

    @NotNull
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
