package com.blazejherzog.jobcandidatemanager.employer.api;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleName;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.Role;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.User;
import com.blazejherzog.jobcandidatemanager.employer.domain.EmployerAdditionalDataCommand;
import com.blazejherzog.jobcandidatemanager.employer.domain.EmployerAdditionalDataService;
import com.blazejherzog.jobcandidatemanager.employer.domain.EmployerData;
import com.blazejherzog.jobcandidatemanager.shared.domain.Address;
import com.blazejherzog.jobcandidatemanager.shared.domain.UserDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmployerAdditionalDataRestResourceTest {

    @Mock
    private UserDataService userDataService;

    @Mock
    private EmployerAdditionalDataService additionalDataService;

    @InjectMocks
    private EmployerAdditionalDataRestResource restResource;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createAdditionalData_ValidRequest_ShouldReturnOk() throws Exception {
        // Given
        Long userId = 1L;
        EmployerAdditionalDataRequest dataRequest = createTestDataRequest();
        User mockUser = createMockUser();
        EmployerData mockEmployerData = createMockEmployerData();

        when(userDataService.getUserById(anyLong())).thenReturn(mockUser);
        when(additionalDataService.createAdditionalData(any(User.class), any(EmployerAdditionalDataCommand.class)))
                .thenReturn(mockEmployerData);

        // When & Then
        mockMvc.perform(post("/api/employer/additional-data")
                        .param("userId", String.valueOf(userId))
                        .content(objectMapper.writeValueAsString(dataRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("John")) // Adjust this based on your expected response
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void createAdditionalData_InvalidUserId_ShouldReturnBadRequest() throws Exception {
        // Given
        Long invalidUserId = -1L;
        EmployerAdditionalDataRequest dataRequest = createTestDataRequest();

        when(userDataService.getUserById(anyLong())).thenReturn(null);

        // When & Then
        mockMvc.perform(post("/api/employer/additional-data")
                        .param("userId", String.valueOf(invalidUserId))
                        .content(objectMapper.writeValueAsString(dataRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private EmployerAdditionalDataRequest createTestDataRequest() {
        return EmployerAdditionalDataRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .isHiring(true)
                .build();
    }

    private User createMockUser() {
        return User.builder()
                .id(1L)
                .username("testUser")
                .email("test@example.com")
                .roles(Collections.singleton(new Role(RoleName.ROLE_EMPLOYER_USER)))
                .build();
    }

    private EmployerData createMockEmployerData() {
        return EmployerData.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .privateAddress(Address.builder()
                        .city("Sample City")
                        .streetAndFullNumber("123 Sample Street")
                        .postalCode("12345")
                        .build())
                .isHiring(true)
                .build();
    }
}