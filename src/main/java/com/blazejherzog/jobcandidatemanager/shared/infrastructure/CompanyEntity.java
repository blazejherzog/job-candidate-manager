package com.blazejherzog.jobcandidatemanager.shared.infrastructure;

import com.blazejherzog.jobcandidatemanager.shared.infrastructure.AddressEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    @OneToOne
    @JoinColumn(name = "company_main_address_id")
    private AddressEntity companyMainAddress;

    @OneToOne
    @JoinColumn(name = "company_branch_address_id")
    private AddressEntity companyBranchAddress;

    private String companyIndustry;

}
