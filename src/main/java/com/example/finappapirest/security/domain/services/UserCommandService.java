package com.example.finappapirest.security.domain.services;

import com.example.finappapirest.security.domain.model.aggregates.User;
import com.example.finappapirest.security.domain.model.commands.ChangeEmailCommand;
import com.example.finappapirest.security.domain.model.commands.ChangePasswordCommand;
import com.example.finappapirest.security.domain.model.commands.SignInCommand;
import com.example.finappapirest.security.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpCommand command);
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);

    User handle(ChangeEmailCommand command);
    User handle(ChangePasswordCommand command);
}
