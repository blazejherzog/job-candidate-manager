package com.blazejherzog.jobcandidatemanager.candidate.domain;

import com.blazejherzog.jobcandidatemanager.candidate.domain.model.CandidateData;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.User;
import com.blazejherzog.jobcandidatemanager.candidate.infrastructure.repository.CandidateDataRepository;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
public class CandidateDataFactory {

    private final CandidateDataRepository repository;

    public CandidateDataFactory(CandidateDataRepository repository) {
        this.repository = repository;
    }

    public CandidateData createAdditionalData(User user, CandidateAdditionalDataCommand command) {
        requireNonNull(user);
        requireNonNull(command);

        return CandidateData.builder()
                .userId(user.getId())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .phoneNumber(command.getPhoneNumber())
                .privateAddress(command.getPrivateAddress())
                .currentCompany(command.getCurrentCompany())
                .currentOccupation(command.getCurrentOccupation())
                .currentIndustry(command.getCurrentIndustry())
                .currentOccupationYearsExperience(command.getCurrentOccupationYearsExperience())
                .skills(command.getSkills())
                .languageProficiencies(command.getLanguageProficiencies())
                .personalSummary(command.getPersonalSummary())
                .hasCvUploaded(command.isHasCvUploaded())
                .build();
    }
}
