package com.example.finappapirest.security.interfaces.rest.resources;

import com.example.finappapirest.security.domain.model.valueobjects.Roles;

import java.util.List;

public record SignUpResource(
        String username, String password,
        List<Roles> roles) {
}
