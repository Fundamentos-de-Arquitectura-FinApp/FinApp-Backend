package com.example.finappapirest.finances.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/accounts")
@Tag(name = "Accounts", description = "Accounts clients management")
public class AccountController {
    @GetMapping()
    @Operation(summary = "Get all accounts", description = "Get all accounts from the system")
    public String getAccounts() {
        return "Accounts";
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by id", description = "Get account by id from the system")
    public String getAccount(@PathVariable("id") Long id) {
        return "Account " + id;
    }

    @PostMapping()
    @Operation(summary = "Open account", description = "Open account in the system")
    public String openAccount() {
        return "Account opened";
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update account", description = "Update account in the system")
    public String updateAccount(@PathVariable("id") Long id) {
        return "Account updated";
    }

    @Operation(summary = "Close account", description = "Close account in the system")
    @DeleteMapping("/{id}")
    public String closeAccount(@PathVariable("id") Long id) {
        return "Account closed";
    }
}
