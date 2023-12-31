package com.blazejherzog.jobcandidatemanager.authentication.infrastructure.repository;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleEntity;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByName(RoleName name);
}
