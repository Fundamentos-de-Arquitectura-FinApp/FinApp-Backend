package com.example.finappapirest.security.domain.services;

import com.example.finappapirest.security.domain.model.commands.ForgotPasswordCommand;
import com.example.finappapirest.security.domain.model.commands.ResetPasswordCommand;

public interface PasswordResetCommandService {
    public void handle(ForgotPasswordCommand command);
    public void handle(ResetPasswordCommand command);
}
