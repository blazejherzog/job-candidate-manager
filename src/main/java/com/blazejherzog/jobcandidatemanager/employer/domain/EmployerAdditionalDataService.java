package com.blazejherzog.jobcandidatemanager.employer.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;

public interface EmployerAdditionalDataService {

    EmployerData createAdditionalData(User user, EmployerAdditionalDataCommand command);
}
