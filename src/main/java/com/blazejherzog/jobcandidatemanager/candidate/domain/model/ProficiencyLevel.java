package com.blazejherzog.jobcandidatemanager.candidate.domain.model;

public enum ProficiencyLevel {

    A1("Beginner"),
    A2("Elementary"),
    B1("Intermediate"),
    B2("Upper Intermediate"),
    C1("Advanced"),
    C2("Proficient");

    private final String description;

    private ProficiencyLevel(String description) {
        this.description = description;
    }
}
