package com.blazejherzog.jobcandidatemanager.shared.api;

import com.blazejherzog.jobcandidatemanager.candidate.domain.model.LanguageProficiency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class AdditionalDataRequest {

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

    private double currentOccupationExperience;

    private List<String> skills;

    private List<LanguageProficiency> languageProficiencies;

    private String personalSummary;

    private boolean hasCvUploaded;

}
