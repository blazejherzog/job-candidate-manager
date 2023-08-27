package com.blazejherzog.jobcandidatemanager.candidate.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.CandidateData;
import com.blazejherzog.jobcandidatemanager.candidate.infrastructure.repository.CandidateDataRepository;
import com.blazejherzog.jobcandidatemanager.shared.domain.AdditionalDataFactory;
import com.blazejherzog.jobcandidatemanager.shared.api.AdditionalDataRequest;
import org.springframework.stereotype.Component;

@Component
public class CandidateDataFactory implements AdditionalDataFactory {

    private final CandidateDataRepository repository;

    public CandidateDataFactory(CandidateDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public CandidateData createAdditionalData(User user, AdditionalDataRequest request) {
        return CandidateData.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build();

    }
}
