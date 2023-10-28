package com.blazejherzog.jobcandidatemanager.candidate.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.CandidateData;

public interface CandidateAdditionalDataService {

    CandidateData createAdditionalData(User user, CandidateAdditionalDataCommand command);
}
