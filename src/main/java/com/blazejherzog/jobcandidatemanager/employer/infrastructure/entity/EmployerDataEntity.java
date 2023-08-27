package com.blazejherzog.jobcandidatemanager.employer.infrastructure.entity;

import jakarta.persistence.*;

@Entity
public class EmployerDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
