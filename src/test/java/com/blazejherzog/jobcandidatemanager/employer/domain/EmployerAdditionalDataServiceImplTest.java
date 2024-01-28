package com.blazejherzog.jobcandidatemanager.employer.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleName;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.Role;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.User;
import com.blazejherzog.jobcandidatemanager.employer.infrastructure.repository.EmployerDataRepository;
import com.blazejherzog.jobcandidatemanager.shared.domain.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployerAdditionalDataServiceImplTest {

    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String PHONE_NUMBER = "123456789";
    private static final String CITY = "Sample City";
    private static final String STREET_AND_FULL_NUMBER = "123 Sample Street";
    private static final String POSTAL_CODE = "12345";
    @Mock
    private EmployerDataFactory factory;

    @Mock
    private EmployerDataRepository repository;

    @InjectMocks
    private EmployerAdditionalDataServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new EmployerAdditionalDataServiceImpl(factory, repository);
    }

    @Test
    void createAdditionalData_ValidRole_ShouldSaveData() {
        // Given
        User user = createUserWithRole(RoleName.ROLE_EMPLOYER_USER);

        EmployerAdditionalDataCommand command = createCommand();
        EmployerData employerData = createEmployerData();

        when(factory.createAdditionalData(user, command)).thenReturn(employerData);

        // When
        EmployerData result = service.createAdditionalData(user, command);

        // Then
        assertThat(result.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(result.getLastName()).isEqualTo(LAST_NAME);
        assertThat(result.getPhoneNumber()).isEqualTo(PHONE_NUMBER);

        verify(repository, times(1)).save(any());
    }

    @Test
    void createAdditionalData_InvalidRole_ShouldThrowException() {
        // Given
        User user = createUserWithRole(RoleName.ROLE_CANDIDATE_USER);

        EmployerAdditionalDataCommand command = createCommand();

        // When & Then
        assertThrows(IllegalStateException.class, () -> service.createAdditionalData(user, command));
    }

    private static User createUserWithRole(RoleName roleName) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(roleName));
        return User.builder()
                .roles(roles)
                .build();
    }

    private static EmployerData createEmployerData() {
        return EmployerData.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .phoneNumber(PHONE_NUMBER)
                .privateAddress(Address.builder()
                        .city(CITY)
                        .streetAndFullNumber(STREET_AND_FULL_NUMBER)
                        .postalCode(POSTAL_CODE)
                        .build())
                .isHiring(true)
                .build();
    }

    private static EmployerAdditionalDataCommand createCommand() {
        return EmployerAdditionalDataCommand.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .phoneNumber(PHONE_NUMBER)
                .isHiring(true)
                .build();
    }
}