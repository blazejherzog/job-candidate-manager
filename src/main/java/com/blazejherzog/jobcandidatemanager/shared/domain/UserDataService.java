package com.blazejherzog.jobcandidatemanager.shared.domain;

import com.blazejherzog.jobcandidatemanager.candidate.domain.model.User;

public interface UserDataService {
    User getUserById(Long userId);
}
