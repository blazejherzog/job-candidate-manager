package com.blazejherzog.jobcandidatemanager.shared.domain;

import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.RoleName;
import com.blazejherzog.jobcandidatemanager.authentication.infrastructure.entity.User;

public class DeterminedUserRoleUtil {

    public static boolean isDeterminedRoleValid(User user, RoleName roleName) {
        return determineUserRole(user).equals(roleName);
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
