package com.blazejherzog.jobcandidatemanager.shared.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.repository.UserRepository;
import com.blazejherzog.jobcandidatemanager.shared.infrastructure.exception.UserNotFoundException;

public class UserDataServiceImpl implements UserDataService {

    private final UserRepository userRepository;

    public UserDataServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(
                String.format("User with id %d not found", userId)));
    }
}
