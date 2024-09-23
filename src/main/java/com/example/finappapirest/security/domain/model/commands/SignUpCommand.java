package com.example.finappapirest.security.domain.model.commands;


import com.example.finappapirest.security.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles) {
}
