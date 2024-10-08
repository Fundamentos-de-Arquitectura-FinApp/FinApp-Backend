package com.example.finappapirest.security.application.internal.commandservices;

import com.example.finappapirest.security.application.internal.outboundservices.hashing.HashingService;
import com.example.finappapirest.security.application.internal.outboundservices.tokens.TokenService;
import com.example.finappapirest.security.domain.model.aggregates.User;
import com.example.finappapirest.security.domain.model.commands.ChangeEmailCommand;
import com.example.finappapirest.security.domain.model.commands.ChangePasswordCommand;
import com.example.finappapirest.security.domain.model.commands.SignInCommand;
import com.example.finappapirest.security.domain.model.commands.SignUpCommand;
import com.example.finappapirest.security.domain.services.UserCommandService;
import com.example.finappapirest.security.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.example.finappapirest.security.infrastructure.persistence.jpa.repositories.UserRepository;
import com.example.finappapirest.shared.interfaces.utils.UserUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username())) throw new RuntimeException("Username already exists");
        var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName())
                .orElseThrow(() -> new RuntimeException("Role name not found"))).toList();
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    @Override
    public User handle(ChangeEmailCommand command) {
        var userId = UserUtils.getCurrentUserId();
        var user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(command.email());
        userRepository.save(user);
        return user;
    }

    @Override
    public User handle(ChangePasswordCommand command) {
        var userId = UserUtils.getCurrentUserId();
        var user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if(!command.oldPassword().equals(command.repeatOldPassword()))
            throw new RuntimeException("Passwords do not match");

        if(!hashingService.matches(command.oldPassword(), user.getPassword()))
            throw new RuntimeException("Invalid password");

        user.setPassword(hashingService.encode(command.newPassword()));
        userRepository.save(user);

        return user;
    }
}
