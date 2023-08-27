package com.blazejherzog.jobcandidatemanager.employer.domain;

import com.blazejherzog.jobcandidatemanager.shared.domain.AdditionalData;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class EmployerData extends AdditionalData {

    private boolean isHiring;
}
