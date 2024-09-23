package com.example.finappapirest.security.application.internal.commandservices;


import com.example.finappapirest.security.domain.model.commands.SeedRolesCommand;
import com.example.finappapirest.security.domain.model.entities.Role;
import com.example.finappapirest.security.domain.model.valueobjects.Roles;
import com.example.finappapirest.security.domain.services.RoleCommandService;
import com.example.finappapirest.security.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role))
                roleRepository.save(new Role(Roles.valueOf(role.name())));
        });
    }
}
