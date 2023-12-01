package com.blazejherzog.jobcandidatemanager.shared.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleEntity;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.UserEntity;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.repository.UserRepository;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.Role;
import com.blazejherzog.jobcandidatemanager.candidate.domain.model.User;
import com.blazejherzog.jobcandidatemanager.shared.infrastructure.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDataServiceImpl implements UserDataService {

    private final UserRepository userRepository;

    public UserDataServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long userId) {
        return entityToModel(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(
                String.format("User with id %d not found", userId))));
    }

    private User entityToModel(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .roles(mapRoles(entity.getRoles()))
                .build();
    }

    private Set<Role> mapRoles(Set<RoleEntity> roles) {
        return roles.stream()
                .map(roleEntity -> Role.builder()
                        .name(roleEntity.getName().name())
                        .build())
                .collect(Collectors.toSet());
    }

}
