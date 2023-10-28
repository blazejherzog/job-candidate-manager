package com.blazejherzog.jobcandidatemanager.employer.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.employer.infrastructure.repository.EmployerDataRepository;
import org.springframework.stereotype.Component;

@Component
public class EmployerDataFactory {

    private final EmployerDataRepository repository;

    public EmployerDataFactory(EmployerDataRepository repository) {
        this.repository = repository;
    }

    public EmployerData createAdditionalData(User user, EmployerAdditionalDataCommand command) {
        return EmployerData.builder()
                .build();
    }
}
