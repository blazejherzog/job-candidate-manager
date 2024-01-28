package com.blazejherzog.jobcandidatemanager.candidate.domain.model;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Role {
    private RoleName name;
}
