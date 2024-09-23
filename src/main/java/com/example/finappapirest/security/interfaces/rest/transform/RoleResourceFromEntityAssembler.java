package com.example.finappapirest.security.interfaces.rest.transform;


import com.example.finappapirest.security.domain.model.entities.Role;
import com.example.finappapirest.security.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
