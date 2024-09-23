package com.example.finappapirest.security.interfaces.rest.transform;


import com.example.finappapirest.security.domain.model.aggregates.User;
import com.example.finappapirest.security.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}
