package com.blazejherzog.jobcandidatemanager.employer.api;

import com.blazejherzog.jobcandidatemanager.employer.domain.EmployerAdditionalDataCommand;

public class EmployerAdditionalDataMapper {

    private EmployerAdditionalDataMapper() {
    }

    static EmployerAdditionalDataCommand mapToCommand(Long userId, EmployerAdditionalDataRequest request) {
        return EmployerAdditionalDataCommand.builder()
                .userId(userId)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .company(request.getCompany())
                .privateAddress(request.getPrivateAddress())
                .phoneNumber(request.getPhoneNumber())
                .isHiring(true)
                .build();
    }
}
