package com.blazejherzog.jobcandidatemanager.employer.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleName;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.shared.domain.DeterminedUserRoleUtil;
import org.springframework.stereotype.Service;

@Service
public class EmployerAdditionalDataServiceImpl implements EmployerAdditionalDataService{

    private final EmployerDataFactory factory;

    public EmployerAdditionalDataServiceImpl(EmployerDataFactory factory) {
        this.factory = factory;
    }

    @Override
    public EmployerData createAdditionalData(User user, EmployerAdditionalDataCommand command) {
        if (DeterminedUserRoleUtil.isDeterminedRoleValid(user, RoleName.ROLE_EMPLOYER_USER)) {
            factory.createAdditionalData(user, command);
        }
        throw new IllegalStateException("Cannot create additional candidate data, ROLE_EMPLOYER_USER is missing.");
    }
}
