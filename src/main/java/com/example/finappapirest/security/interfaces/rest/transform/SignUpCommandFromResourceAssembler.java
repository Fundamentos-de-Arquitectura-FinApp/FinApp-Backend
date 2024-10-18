package com.example.finappapirest.security.interfaces.rest.transform;

import com.example.finappapirest.security.domain.model.commands.SignUpCommand;
import com.example.finappapirest.security.domain.model.entities.Role;
import com.example.finappapirest.security.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource signUpResource) {
        var roles = signUpResource.roles() != null ? signUpResource.roles().stream()
                .map(role -> Role.toRoleFromName(role.name()))
                .toList() : new ArrayList<Role>();
        return new SignUpCommand(signUpResource.username(), signUpResource.password(), roles);
    }
}
