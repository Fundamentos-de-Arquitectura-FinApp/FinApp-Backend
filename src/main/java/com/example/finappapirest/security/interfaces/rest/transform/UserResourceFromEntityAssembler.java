package com.example.finappapirest.security.interfaces.rest.transform;

import com.example.finappapirest.security.domain.model.aggregates.User;
import com.example.finappapirest.security.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(role -> role.getName().name()).toList();
        return new UserResource(user.getId(), user.getUsername(), roles);
    }
}
