package com.blazejherzog.jobcandidatemanager.shared.domain;

import com.blazejherzog.jobcandidatemanager.shared.api.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalData {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address privateAddress;
}
