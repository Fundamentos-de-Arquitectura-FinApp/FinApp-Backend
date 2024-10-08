package com.example.finappapirest.security.interfaces.rest.resources;

public record ChangePasswordResource(
        String oldPassword,
        String repeatOldPassword,
        String newPassword
) {
}
