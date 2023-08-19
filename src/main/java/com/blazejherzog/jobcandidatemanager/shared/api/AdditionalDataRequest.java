package com.blazejherzog.jobcandidatemanager.shared.api;

import java.util.List;

public class AdditionalDataRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address privateAddress;
    private Company currentCompany;
    private String currentOccupation;
    private String currentIndustry;
    private double currentOccupationExperience;
    private List<String> skills;
    private String personalSummary;
    private boolean hasCvUploaded;

}
