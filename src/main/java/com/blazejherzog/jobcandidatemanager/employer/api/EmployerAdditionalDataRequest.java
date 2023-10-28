package com.blazejherzog.jobcandidatemanager.employer.api;

import com.blazejherzog.jobcandidatemanager.shared.domain.Address;
import com.blazejherzog.jobcandidatemanager.shared.domain.Company;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployerAdditionalDataRequest {

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

    private Company company;

    private boolean isHiring;
}
