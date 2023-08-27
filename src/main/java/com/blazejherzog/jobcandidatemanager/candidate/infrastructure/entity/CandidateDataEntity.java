package com.blazejherzog.jobcandidatemanager.candidate.infrastructure.entity;

import jakarta.persistence.*;

@Entity
public class CandidateDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
