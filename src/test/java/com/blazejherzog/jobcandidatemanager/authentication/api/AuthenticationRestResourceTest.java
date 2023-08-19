package com.blazejherzog.jobcandidatemanager.authentication.api;

import com.blazejherzog.jobcandidatemanager.authentication.domain.UserDetailsImpl;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.authentication.jwt.JwtUtils;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.repository.RoleRepository;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:db/create_roles.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
public class AuthenticationRestResourceTest {

    private static final int ZERO_MAX_AGE = 0;
    private static final String CANDIDATE_ROLE = "candidate";
    private static final String USERNAME = "testuser";
    private static final String EMAIL = "testuser@example.com";
    private static final String INVALID_ROLE = "invalid";
    private static final String PASSWORD = "testpassword";
    private static final String INVALID_PASSWORD = "invalidpassword";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private AuthenticationManager authenticationManager;


    @Test
    void shouldRegisterUserSuccessfully() throws Exception {
        //given
        RegistrationRequest request = registrationRequest(Collections.singleton(CANDIDATE_ROLE));

        //when
        mockMvc.perform(post("/api/authentication/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User registered successfully!"));

        //then
        Optional<User> optionalUser = userRepository.findByUsername(USERNAME);
        assertThat(optionalUser).isPresent();
        assertThat(optionalUser).hasValueSatisfying(user -> {
            assertThat(user.getUsername()).isEqualTo(USERNAME);
            assertThat(user.getEmail()).isEqualTo(EMAIL);
        });
    }

    @Test
    void shouldThrowExceptionWhenInvalidRoleSelected() throws Exception {
        //given
        RegistrationRequest request = registrationRequest(Collections.singleton(INVALID_ROLE));

        //when and then
        mockMvc.perform(post("/api/authentication/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Invalid role selection. Choose either 'candidate' or 'employer'."));
    }

    @Test
    public void shouldAuthenticateUserAndReturnUserInfo() throws Exception {
        // given
        UserDetailsImpl userDetails = new UserDetailsImpl(
                1L, USERNAME, EMAIL, PASSWORD,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_CANDIDATE_USER"))
        );

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        given(authenticationManager.authenticate(any(Authentication.class))).willReturn(authentication);

        ResponseCookie jwtCookie = createJwtCookie();

        given(jwtUtils.generateJwtCookie(userDetails)).willReturn(jwtCookie);

        LoginRequest loginRequest = new LoginRequest(USERNAME, PASSWORD);

        //when and then
        mockMvc.perform(post("/api/authentication/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(loginRequest))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value(USERNAME))
                .andExpect(jsonPath("$.email").value(EMAIL))
                .andExpect(jsonPath("$.roles[0]").value("ROLE_CANDIDATE_USER"))
                .andExpect(header().exists(HttpHeaders.SET_COOKIE))
                .andExpect(header().string(HttpHeaders.SET_COOKIE, jwtCookie.toString()));
    }

    @Test
    public void shouldReturnUnauthorizedForInvalidCredentials() throws Exception {
        //given
        given(authenticationManager.authenticate(any(Authentication.class)))
                .willThrow(new BadCredentialsException("Invalid credentials"));

        LoginRequest loginRequest = new LoginRequest(USERNAME, INVALID_PASSWORD);

        //when and then
        mockMvc.perform(post("/api/authentication/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(loginRequest))
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldLogoutUserAndClearJwtCookie() throws Exception {
        // Mock clean JWT cookie
        ResponseCookie jwtCookie = createJwtCookie(ZERO_MAX_AGE);
        given(jwtUtils.getCleanJwtCookie()).willReturn(jwtCookie);

        mockMvc.perform(post("/api/authentication/logout"))
                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.SET_COOKIE))
                .andExpect(content().json("{\"message\": \"You have been signed out\"}"));
    }

    private static RegistrationRequest registrationRequest(Set<String> role) {
        RegistrationRequest request = new RegistrationRequest();
        request.setUsername(USERNAME);
        request.setEmail(EMAIL);
        request.setPassword(PASSWORD);
        request.setRole(role);
        return request;
    }

    private static String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ResponseCookie createJwtCookie() {
        return ResponseCookie.from("jwtToken", "mockJwtToken")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .build();
    }

    @NotNull
    private static ResponseCookie createJwtCookie(int maxAge) {
        return ResponseCookie.from("jwtToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(maxAge)
                .build();
    }
}