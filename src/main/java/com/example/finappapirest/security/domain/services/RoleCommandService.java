package com.example.finappapirest.security.domain.services;


import com.example.finappapirest.security.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
