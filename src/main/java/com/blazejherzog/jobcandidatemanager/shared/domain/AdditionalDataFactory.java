package com.blazejherzog.jobcandidatemanager.shared.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.shared.api.AdditionalDataRequest;

public interface AdditionalDataFactory {
    AdditionalData createAdditionalData(User user, AdditionalDataRequest request);

}
