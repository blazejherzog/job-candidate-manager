package com.blazejherzog.jobcandidatemanager.shared.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.shared.api.AdditionalDataRequest;

public interface UserDataService {
    User getUserById(Long userId);

    void saveUserAdditionalData(User user, AdditionalDataRequest dataRequest);
}
