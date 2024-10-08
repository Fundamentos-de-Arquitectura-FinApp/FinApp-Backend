package com.example.finappapirest.security.domain.model.commands;

public record ChangePasswordCommand(
        String oldPassword,
        String repeatOldPassword,
        String newPassword
) {
}
