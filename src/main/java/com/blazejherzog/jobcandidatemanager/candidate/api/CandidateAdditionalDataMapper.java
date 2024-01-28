package com.blazejherzog.jobcandidatemanager.candidate.api;

import com.blazejherzog.jobcandidatemanager.candidate.domain.CandidateAdditionalDataCommand;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.LanguageProficiency;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.ProficiencyLevel;

import java.util.List;

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
                .privateAddress(request.getAddress())
                .currentCompanyName(request.getCurrentCompanyName())
                .currentOccupation(request.getCurrentOccupation())
                .currentIndustry(request.getCurrentIndustry())
                .currentOccupationYearsExperience(request.getCurrentOccupationYearsExperience())
                .skills(request.getSkills())
                .languageProficiencies(mapToLanguageProficiencies(request))
                .personalSummary(request.getPersonalSummary())
                .hasCvUploaded(request.isHasCvUploaded())
                .build();
    }

    private static List<LanguageProficiency> mapToLanguageProficiencies(CandidateAdditionalDataRequest request) {
        return request.getLanguageProficiencies().entrySet().stream()
                .map(entry -> new LanguageProficiency(entry.getKey(), ProficiencyLevel.valueOf(entry.getValue()))).toList();
    }
}
