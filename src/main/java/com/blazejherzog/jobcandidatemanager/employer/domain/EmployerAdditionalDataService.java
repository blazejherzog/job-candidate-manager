package com.blazejherzog.jobcandidatemanager.employer.domain;

import com.blazejherzog.jobcandidatemanager.candidate.domain.model.User;

public interface EmployerAdditionalDataService {

    EmployerData createAdditionalData(User user, EmployerAdditionalDataCommand command);
}
