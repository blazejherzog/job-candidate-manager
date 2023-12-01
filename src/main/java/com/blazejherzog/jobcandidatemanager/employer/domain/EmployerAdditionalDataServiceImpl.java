package com.blazejherzog.jobcandidatemanager.employer.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleName;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.User;
import com.blazejherzog.jobcandidatemanager.employer.infrastructure.entity.EmployerDataEntity;
import com.blazejherzog.jobcandidatemanager.employer.infrastructure.repository.EmployerDataRepository;
import com.blazejherzog.jobcandidatemanager.shared.domain.DeterminedUserRoleUtil;
import org.springframework.stereotype.Service;

@Service
public class EmployerAdditionalDataServiceImpl implements EmployerAdditionalDataService{

    private final EmployerDataFactory factory;

    private final EmployerDataRepository repository;

    public EmployerAdditionalDataServiceImpl(EmployerDataFactory factory, EmployerDataRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    @Override
    public EmployerData createAdditionalData(User user, EmployerAdditionalDataCommand command) {
        if (DeterminedUserRoleUtil.isDeterminedRoleValid(user, RoleName.ROLE_EMPLOYER_USER)) {
            EmployerData employerData = factory.createAdditionalData(user, command);
            repository.save(modelToEntity(employerData));
            return employerData;
        }
        throw new IllegalStateException("Cannot create additional candidate data, ROLE_EMPLOYER_USER is missing.");
    }

    private EmployerDataEntity modelToEntity(EmployerData employerData) {
        return EmployerDataEntity.builder()
                //TODO
                .build();
    }
}
