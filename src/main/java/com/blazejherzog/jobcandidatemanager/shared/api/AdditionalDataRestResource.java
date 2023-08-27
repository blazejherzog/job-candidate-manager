package com.blazejherzog.jobcandidatemanager.shared.api;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.shared.domain.UserDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/additional-data")
public class AdditionalDataRestResource {

    private final UserDataService userDataService;

    public AdditionalDataRestResource(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @PostMapping
    public ResponseEntity<String> createAdditionalData(@RequestParam Long userId, @RequestBody AdditionalDataRequest dataRequest) {
        User user = userDataService.getUserById(userId);
        userDataService.saveUserAdditionalData(user, dataRequest);
        return ResponseEntity.ok("Additional data created successfully");
    }
}
