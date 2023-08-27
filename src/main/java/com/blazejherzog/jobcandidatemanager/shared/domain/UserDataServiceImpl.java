package com.blazejherzog.jobcandidatemanager.shared.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleName;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.repository.UserRepository;
import com.blazejherzog.jobcandidatemanager.shared.api.AdditionalDataRequest;
import com.blazejherzog.jobcandidatemanager.shared.infrastructure.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserDataServiceImpl implements UserDataService {

    private final UserRepository userRepository;

    private final Map<RoleName, AdditionalDataFactory> roleFactoryMap;

    public UserDataServiceImpl(UserRepository userRepository, Map<RoleName, AdditionalDataFactory> roleFactoryMap) {
        this.userRepository = userRepository;
        this.roleFactoryMap = roleFactoryMap;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(
                String.format("User with id %d not found", userId)));
    }

    @Override
    public void saveUserAdditionalData(User user, AdditionalDataRequest dataRequest) {
        RoleName roleName = determineUserRole(user);
        AdditionalDataFactory factory = roleFactoryMap.get(roleName);
        factory.createAdditionalData(user, dataRequest);
    }

    private static RoleName determineUserRole(User user) {
        if (userHasCandidateRole(user)) {
            return RoleName.ROLE_CANDIDATE_USER;
        } else if (userHasEmployerRole(user)) {
            return RoleName.ROLE_EMPLOYER_USER;
        } else {
            throw new IllegalArgumentException("User role not recognized");
        }
    }

    private static boolean userHasCandidateRole(User user) {
        return user.getRoles().stream()
                .anyMatch(role -> role.getName().equals(RoleName.ROLE_CANDIDATE_USER));
    }

    private static boolean userHasEmployerRole(User user) {
        return user.getRoles().stream()
                .anyMatch(role -> role.getName().equals(RoleName.ROLE_EMPLOYER_USER));
    }
}
