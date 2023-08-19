package com.blazejherzog.jobcandidatemanager.shared.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;

public interface UserDataService {
    User getUserById(Long userId);
}
