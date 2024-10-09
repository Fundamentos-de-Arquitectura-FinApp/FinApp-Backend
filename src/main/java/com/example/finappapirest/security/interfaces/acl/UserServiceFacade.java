package com.example.finappapirest.security.interfaces.acl;

import com.example.finappapirest.security.domain.model.aggregates.User;
import com.example.finappapirest.security.domain.model.commands.SignUpCommand;
import com.example.finappapirest.security.domain.model.valueobjects.Roles;
import com.example.finappapirest.security.domain.services.UserCommandService;
import com.example.finappapirest.security.domain.services.UserQueryService;
import com.example.finappapirest.security.interfaces.rest.resources.SignUpResource;
import com.example.finappapirest.security.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.example.finappapirest.shared.domain.model.exceptions.InternalServerErrorException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceFacade {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public Long createUser(String email, String password, List<Roles>roles){
        var resource = new SignUpResource(email,password,roles);
        var command = SignUpCommandFromResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(command);
        if(user.isEmpty()){
            throw new InternalServerErrorException("User not created");
        }
        return user.get().getId();
    }
}
