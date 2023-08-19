package com.blazejherzog.jobcandidatemanager.authentication.api;

import com.blazejherzog.jobcandidatemanager.authentication.domain.UserDetailsImpl;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.Role;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleName;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.authentication.jwt.JwtUtils;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.repository.RoleRepository;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationRestResource {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie responseCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(new UserInfoResponse(
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles
                ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest request) {
        try {
            validateUsernameAndEmailAvailability(request);

            User user = createUserFromRegistrationRequest(request);

            Set<String> requestRoles = request.getRole();

            if (requestRoles == null || requestRoles.isEmpty()) {
                return ResponseEntity.badRequest().body("Error: Please select a role. Available roles are 'candidate' or 'employer'.");
            } else if (requestRoles.size() == 1) {
                String requestedRole = requestRoles.iterator().next();
                switch (requestedRole) {
                    case "candidate":
                        addRoleToUser(RoleName.ROLE_CANDIDATE_USER, user);
                        break;
                    case "employer":
                        addRoleToUser(RoleName.ROLE_EMPLOYER_USER, user);
                        break;
                    default:
                        return ResponseEntity.badRequest().body("Error: Invalid role selection. Choose either 'candidate' or 'employer'.");
                }
            } else {
                return ResponseEntity.badRequest().body("Error: Invalid role selection. Choose only one role, either 'candidate' or 'employer'.");
            }

            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during user registration.");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You have been signed out"));
    }

    private void validateUsernameAndEmailAvailability(RegistrationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already taken!");
        }
    }

    private User createUserFromRegistrationRequest(RegistrationRequest request) {
        return new User(request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()));
    }

    private void addRoleToUser(RoleName roleName, User user) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role '" + roleName + "' is not found."));
        user.getRoles().add(role);
    }
}
