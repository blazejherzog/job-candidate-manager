package com.blazejherzog.jobcandidatemanager.candidate.domain;

import com.blazejherzog.jobcandidatemanager.candidate.domain.model.CandidateData;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.User;

public interface CandidateAdditionalDataService {

    CandidateData createAdditionalData(User user, CandidateAdditionalDataCommand command);
}
