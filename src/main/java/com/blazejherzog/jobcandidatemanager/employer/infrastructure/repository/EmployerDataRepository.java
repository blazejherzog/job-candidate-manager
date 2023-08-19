package com.blazejherzog.jobcandidatemanager.employer.infrastructure.repository;

import com.blazejherzog.jobcandidatemanager.employer.infrastructure.entity.EmployerDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerDataRepository extends JpaRepository<EmployerDataEntity, Long> {
}
