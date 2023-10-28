package com.blazejherzog.jobcandidatemanager.employer.domain;

import com.blazejherzog.jobcandidatemanager.shared.domain.Address;
import com.blazejherzog.jobcandidatemanager.shared.domain.Company;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployerAdditionalDataCommand{

    private Long userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address privateAddress;
    private Company company;
    private boolean isHiring;
}
