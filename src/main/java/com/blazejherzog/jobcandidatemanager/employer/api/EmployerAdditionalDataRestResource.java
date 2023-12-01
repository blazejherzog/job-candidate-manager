package com.blazejherzog.jobcandidatemanager.employer.api;

import com.blazejherzog.jobcandidatemanager.candidate.domain.model.User;
import com.blazejherzog.jobcandidatemanager.employer.domain.EmployerAdditionalDataService;
import com.blazejherzog.jobcandidatemanager.employer.domain.EmployerData;
import com.blazejherzog.jobcandidatemanager.employer.domain.EmployerAdditionalDataCommand;
import com.blazejherzog.jobcandidatemanager.shared.domain.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.blazejherzog.jobcandidatemanager.employer.api.EmployerAdditionalDataMapper.mapToCommand;

@RestController
@RequestMapping("/api/employer/additional-data")
public class EmployerAdditionalDataRestResource {

    private final UserDataService userDataService;
    private final EmployerAdditionalDataService additionalDataService;

    public EmployerAdditionalDataRestResource(UserDataService userDataService, EmployerAdditionalDataService additionalDataService) {
        this.userDataService = userDataService;
        this.additionalDataService = additionalDataService;
    }

    @PostMapping
    public ResponseEntity<EmployerData> createAdditionalData(@RequestParam Long userId, @RequestBody EmployerAdditionalDataRequest dataRequest) {
        User user = userDataService.getUserById(userId);
        EmployerAdditionalDataCommand additionalDataCommand = mapToCommand(userId, dataRequest);
        EmployerData employerData = additionalDataService.createAdditionalData(user, additionalDataCommand);
        return ResponseEntity.ok(employerData);
    }
}
