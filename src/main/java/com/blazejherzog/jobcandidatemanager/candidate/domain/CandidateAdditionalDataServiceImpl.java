package com.blazejherzog.jobcandidatemanager.candidate.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleName;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.CandidateData;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.User;
import com.blazejherzog.jobcandidatemanager.candidate.infrastructure.entity.CandidateDataEntity;
import com.blazejherzog.jobcandidatemanager.candidate.infrastructure.repository.CandidateDataRepository;
import com.blazejherzog.jobcandidatemanager.shared.domain.DeterminedUserRoleUtil;
import org.springframework.stereotype.Service;

@Service
public class CandidateAdditionalDataServiceImpl implements CandidateAdditionalDataService{

    private final CandidateDataFactory factory;

    private final CandidateDataRepository repository;

    public CandidateAdditionalDataServiceImpl(CandidateDataFactory factory, CandidateDataRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    @Override
    public CandidateData createAdditionalData(User user, CandidateAdditionalDataCommand command) {
        if (DeterminedUserRoleUtil.isDeterminedRoleValid(user, RoleName.ROLE_CANDIDATE_USER)) {
            CandidateData candidateData = factory.createAdditionalData(user, command);
            repository.save(modelToEntity(candidateData));
            return candidateData;
        }
        throw new IllegalStateException("Cannot create additional candidate data, ROLE_CANDIDATE_USER is missing.");
    }

    private CandidateDataEntity modelToEntity(CandidateData candidateData) {
        return CandidateDataEntity.builder()
                //TODO
                .build();
    }


}
