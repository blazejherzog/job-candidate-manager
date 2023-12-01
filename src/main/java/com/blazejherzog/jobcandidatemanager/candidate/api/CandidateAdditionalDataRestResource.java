package com.blazejherzog.jobcandidatemanager.candidate.api;

import com.blazejherzog.jobcandidatemanager.candidate.domain.CandidateAdditionalDataService;
import com.blazejherzog.jobcandidatemanager.candidate.domain.CandidateAdditionalDataCommand;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.User;
import com.blazejherzog.jobcandidatemanager.shared.domain.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.blazejherzog.jobcandidatemanager.candidate.api.CandidateAdditionalDataMapper.mapToCommand;

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
        CandidateAdditionalDataCommand additionalDataCommand = mapToCommand(userId, dataRequest);
        additionalDataService.createAdditionalData(user, additionalDataCommand);
        return ResponseEntity.ok("Additional data created successfully");
    }
}
