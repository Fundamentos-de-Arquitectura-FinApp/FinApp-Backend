package com.example.finappapirest.security.domain.model.commands;

public record ResetPasswordCommand(String password, String token) {
}
