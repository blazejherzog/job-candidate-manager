package com.blazejherzog.jobcandidatemanager.shared.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Address {

    private String city;
    private String streetAndFullNumber;
    private String postalCode;
}
