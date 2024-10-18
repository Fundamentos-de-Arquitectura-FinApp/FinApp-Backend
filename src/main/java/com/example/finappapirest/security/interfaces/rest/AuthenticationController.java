package com.example.finappapirest.security.interfaces.rest;

import com.example.finappapirest.security.domain.services.UserCommandService;
import com.example.finappapirest.security.interfaces.rest.resources.AuthenticatedUserResource;
import com.example.finappapirest.security.interfaces.rest.resources.SignInResource;
import com.example.finappapirest.security.interfaces.rest.resources.SignUpResource;
import com.example.finappapirest.security.interfaces.rest.resources.UserResource;
import com.example.finappapirest.security.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.example.finappapirest.security.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.example.finappapirest.security.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.example.finappapirest.security.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Management Endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;

    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Sign in", description = "Sign in with email and password")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);
        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(
                authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }

    @PostMapping("/sign-up")
    @Operation(summary = "Sign up", description = "Sign up with email, password and role")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
}
