package com.blazejherzog.jobcandidatemanager.candidate.infrastructure.repository;

import com.blazejherzog.jobcandidatemanager.candidate.infrastructure.entity.CandidateDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateDataRepository extends JpaRepository<CandidateDataEntity, Long> {
}
