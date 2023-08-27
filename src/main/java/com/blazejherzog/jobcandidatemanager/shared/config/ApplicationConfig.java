package com.blazejherzog.jobcandidatemanager.shared.config;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleName;
import com.blazejherzog.jobcandidatemanager.candidate.domain.CandidateDataFactory;
import com.blazejherzog.jobcandidatemanager.employer.domain.EmployerDataFactory;
import com.blazejherzog.jobcandidatemanager.shared.domain.AdditionalDataFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationConfig {

    @Bean
    public Map<RoleName, AdditionalDataFactory> roleFactoryMap(
            CandidateDataFactory candidateDataFactory,
            EmployerDataFactory employerDataFactory
    ) {
        Map<RoleName, AdditionalDataFactory> map = new HashMap<>();
        map.put(RoleName.ROLE_CANDIDATE_USER, candidateDataFactory);
        map.put(RoleName.ROLE_EMPLOYER_USER, employerDataFactory);
        return map;
    }
}
