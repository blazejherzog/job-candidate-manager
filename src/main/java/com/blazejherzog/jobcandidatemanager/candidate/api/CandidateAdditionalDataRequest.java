package com.blazejherzog.jobcandidatemanager.candidate.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

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

    @NotBlank
    private String address;

    @NotBlank
    private String currentCompanyName;

    @NotBlank
    private String currentOccupation;

    @NotBlank
    private String currentIndustry;

    @NotBlank
    private double currentOccupationYearsExperience;

    @NotNull
    private List<String> skills;

    @NotNull
    private Map<String, String> languageProficiencies;

    @NotBlank
    private String personalSummary;

    private boolean hasCvUploaded;
}
