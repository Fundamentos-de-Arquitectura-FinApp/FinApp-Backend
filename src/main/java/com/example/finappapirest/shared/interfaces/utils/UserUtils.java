package com.example.finappapirest.shared.interfaces.utils;

import com.example.finappapirest.security.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.example.finappapirest.shared.domain.model.exceptions.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetailsImpl) {
                return ((UserDetailsImpl) principal).getId();
            }
        }
        throw  new BadRequestException("User not authenticated");
    }
    public static String getCurrentUserEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetailsImpl) {
                return ((UserDetailsImpl) principal).getUsername();
            }
        }
        throw  new BadRequestException("User not authenticated");
    }
}
