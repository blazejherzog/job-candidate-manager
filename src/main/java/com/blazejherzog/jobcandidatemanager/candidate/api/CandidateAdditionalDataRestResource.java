package com.blazejherzog.jobcandidatemanager.candidate.api;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.candidate.domain.CandidateAdditionalDataService;
import com.blazejherzog.jobcandidatemanager.candidate.domain.CandidateAdditionalDataCommand;
import com.blazejherzog.jobcandidatemanager.shared.domain.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate/additional-data")
public class CandidateAdditionalDataRestResource {

    private final UserDataService userDataService;
    private final CandidateAdditionalDataService additionalDataService;

    public CandidateAdditionalDataRestResource(UserDataService userDataService, CandidateAdditionalDataService additionalDataService) {
        this.userDataService = userDataService;
        this.additionalDataService = additionalDataService;
    }

    @PostMapping
    public ResponseEntity<String> createAdditionalData(@RequestParam Long userId, @RequestBody CandidateAdditionalDataRequest dataRequest) {
        User user = userDataService.getUserById(userId);
        CandidateAdditionalDataCommand additionalDataCommand = createAdditionalDataCommand(userId, dataRequest);
        additionalDataService.createAdditionalData(user, additionalDataCommand);
        return ResponseEntity.ok("Additional data created successfully");
    }

    private CandidateAdditionalDataCommand createAdditionalDataCommand(Long userId, CandidateAdditionalDataRequest request) {
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
