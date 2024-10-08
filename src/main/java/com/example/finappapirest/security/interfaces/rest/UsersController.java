package com.example.finappapirest.security.interfaces.rest;

import com.example.finappapirest.security.application.internal.commandservices.UserCommandServiceImpl;
import com.example.finappapirest.security.domain.model.commands.ChangeEmailCommand;
import com.example.finappapirest.security.domain.model.commands.ChangePasswordCommand;
import com.example.finappapirest.security.domain.model.queries.GetAllUsersQuery;
import com.example.finappapirest.security.domain.model.queries.GetUserByIdQuery;
import com.example.finappapirest.security.domain.services.UserQueryService;
import com.example.finappapirest.security.interfaces.rest.resources.ChangeEmailResource;
import com.example.finappapirest.security.interfaces.rest.resources.ChangePasswordResource;
import com.example.finappapirest.security.interfaces.rest.resources.UserResource;
import com.example.finappapirest.security.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {
    private final UserQueryService userQueryService;
    private final UserCommandServiceImpl userCommandServiceImpl;

    public UsersController(UserQueryService userQueryService, UserCommandServiceImpl userCommandServiceImpl) {
        this.userQueryService = userQueryService;
        this.userCommandServiceImpl = userCommandServiceImpl;
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Admin users can get all users")
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by id", description = "Admin users can get a user by id or clients can get their own user by id")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId){
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty())
            return ResponseEntity.notFound().build();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }
    @PatchMapping("/change-email")
    @Operation(summary = "Change email", description = "Change email of the user")
    public ResponseEntity<UserResource> changeEmail(@RequestBody ChangeEmailResource changeEmailResource){
        var command = new ChangeEmailCommand(changeEmailResource.email());
        var user = userCommandServiceImpl.handle(command);
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);
        return ResponseEntity.ok(userResource);
    }

    @PatchMapping("/change-password")
    @Operation(summary = "Change password", description = "Change password of the user")
    public ResponseEntity<UserResource> changePassword(@RequestBody ChangePasswordResource resource){
        var command = new ChangePasswordCommand(resource.oldPassword(),resource.repeatOldPassword(),resource.newPassword());
        var user = userCommandServiceImpl.handle(command);
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);
        return ResponseEntity.ok(userResource);
    }
}
