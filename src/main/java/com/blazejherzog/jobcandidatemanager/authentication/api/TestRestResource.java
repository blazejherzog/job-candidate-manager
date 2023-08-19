package com.blazejherzog.jobcandidatemanager.authentication.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestRestResource {

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/candidate")
    @PreAuthorize("hasRole('CANDIDATE_USER')")
    public String userAccess() {
        return "Candidate Content.";
    }

    @GetMapping("/employer")
    @PreAuthorize("hasRole('EMPLOYER_USER')")
    public String moderatorAccess() {
        return "Employer Board.";
    }
}
