package com.blazejherzog.jobcandidatemanager.candidate.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.CandidateData;
import com.blazejherzog.jobcandidatemanager.candidate.infrastructure.repository.CandidateDataRepository;

public class CandidateDataFactory {


    private final CandidateDataRepository repository;

    public CandidateDataFactory(CandidateDataRepository repository) {
        this.repository = repository;
    }

    public CandidateData createAdditionalData(User user, CandidateAdditionalDataCommand command) {
        return CandidateData.builder().build();
    }
}
