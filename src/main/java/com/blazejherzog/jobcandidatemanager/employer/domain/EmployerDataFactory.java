package com.blazejherzog.jobcandidatemanager.employer.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.employer.infrastructure.repository.EmployerDataRepository;
import com.blazejherzog.jobcandidatemanager.shared.domain.AdditionalData;
import com.blazejherzog.jobcandidatemanager.shared.domain.AdditionalDataFactory;
import com.blazejherzog.jobcandidatemanager.shared.api.AdditionalDataRequest;
import org.springframework.stereotype.Component;

@Component
public class EmployerDataFactory implements AdditionalDataFactory {

    private final EmployerDataRepository repository;

    public EmployerDataFactory(EmployerDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public AdditionalData createAdditionalData(User user, AdditionalDataRequest request) {
        return null;
    }
}
