package com.blazejherzog.jobcandidatemanager.shared.api;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleName;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.candidate.domain.CandidateDataFactory;
import com.blazejherzog.jobcandidatemanager.employer.domain.EmployerDataFactory;
import com.blazejherzog.jobcandidatemanager.shared.domain.AdditionalDataFactory;
import com.blazejherzog.jobcandidatemanager.shared.domain.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/additionalData")
public class AdditionalDataRestResource {

    private final UserDataService userDataService;
    private final CandidateDataFactory candidateDataFactory;
    private final EmployerDataFactory employerDataFactory;

    public AdditionalDataRestResource(UserDataService userDataService, CandidateDataFactory candidateDataFactory, EmployerDataFactory employerDataFactory) {
        this.userDataService = userDataService;
        this.candidateDataFactory = candidateDataFactory;
        this.employerDataFactory = employerDataFactory;
    }

    @PostMapping
    public ResponseEntity<String> createAdditionalData(@RequestParam Long userId, @RequestBody AdditionalDataRequest dataRequest) {
        User user = userDataService.getUserById(userId);

        AdditionalDataFactory factory;
        if (userHasCandidateRole(user)) {
            factory = candidateDataFactory;
        } else if (userHasEmployerRole(user)) {
            factory = employerDataFactory;
        } else {
            return ResponseEntity.badRequest().body("User does not have a valid role for additional data");
        }

        factory.createAdditionalData(user, dataRequest);
        return ResponseEntity.ok("Additional data created successfully");
    }

    private boolean userHasCandidateRole(User user) {
        return user.getRoles().stream()
                .anyMatch(role -> role.getName() == RoleName.ROLE_CANDIDATE_USER);
    }

    private boolean userHasEmployerRole(User user) {
        return user.getRoles().stream()
                .anyMatch(role -> role.getName() == RoleName.ROLE_EMPLOYER_USER);
    }
}
