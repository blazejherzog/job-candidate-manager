package com.blazejherzog.jobcandidatemanager.candidate.api;

import com.blazejherzog.jobcandidatemanager.candidate.domain.CandidateAdditionalDataCommand;

import static java.util.Objects.requireNonNull;

public class CandidateAdditionalDataMapper {

    private CandidateAdditionalDataMapper() {
    }

    static CandidateAdditionalDataCommand mapToCommand(Long userId, CandidateAdditionalDataRequest request) {
        requireNonNull(userId);
        requireNonNull(request);

        return CandidateAdditionalDataCommand.builder()
                .userId(userId)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .privateAddress(request.getPrivateAddress())
                .currentCompany(request.getCurrentCompany())
                .currentOccupation(request.getCurrentOccupation())
                .currentIndustry(request.getCurrentIndustry())
                .currentOccupationYearsExperience(request.getCurrentOccupationYearsExperience())
                .skills(request.getSkills())
                .languageProficiencies(request.getLanguageProficiencies())
                .personalSummary(request.getPersonalSummary())
                .hasCvUploaded(request.isHasCvUploaded())
                .build();
    }
}
