package com.blazejherzog.jobcandidatemanager.shared.domain;

import com.blazejherzog.jobcandidatemanager.shared.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Company {

    private String companyName;
    private Address companyMainAddress;
    private Address companyBranchAddress;
    private String companyIndustry;

}
