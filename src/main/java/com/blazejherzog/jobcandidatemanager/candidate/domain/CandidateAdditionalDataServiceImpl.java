package com.blazejherzog.jobcandidatemanager.candidate.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleName;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.CandidateData;
import com.blazejherzog.jobcandidatemanager.shared.domain.DeterminedUserRoleUtil;
import org.springframework.stereotype.Service;

@Service
public class CandidateAdditionalDataServiceImpl implements CandidateAdditionalDataService{

    private final CandidateDataFactory factory;

    public CandidateAdditionalDataServiceImpl(CandidateDataFactory factory) {
        this.factory = factory;
    }

    @Override
    public CandidateData createAdditionalData(User user, CandidateAdditionalDataCommand command) {
        if (DeterminedUserRoleUtil.isDeterminedRoleValid(user, RoleName.ROLE_CANDIDATE_USER)) {
            return factory.createAdditionalData(user, command);
        }
        throw new IllegalStateException("Cannot create additional candidate data, ROLE_CANDIDATE_USER is missing.");
    }


}
