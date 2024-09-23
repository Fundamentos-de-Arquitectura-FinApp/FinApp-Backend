package com.example.finappapirest.security.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {
}
