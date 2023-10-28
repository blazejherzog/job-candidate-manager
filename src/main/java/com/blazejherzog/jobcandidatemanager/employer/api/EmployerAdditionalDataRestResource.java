package com.blazejherzog.jobcandidatemanager.employer.api;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.employer.domain.EmployerAdditionalDataService;
import com.blazejherzog.jobcandidatemanager.employer.domain.EmployerData;
import com.blazejherzog.jobcandidatemanager.employer.domain.EmployerAdditionalDataCommand;
import com.blazejherzog.jobcandidatemanager.shared.domain.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        EmployerAdditionalDataCommand additionalDataCommand = createAdditionalDataCommand(userId, dataRequest);
        EmployerData employerData = additionalDataService.createAdditionalData(user, additionalDataCommand);
        return ResponseEntity.ok(employerData);
    }

    private EmployerAdditionalDataCommand createAdditionalDataCommand(Long userId, EmployerAdditionalDataRequest request) {
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
