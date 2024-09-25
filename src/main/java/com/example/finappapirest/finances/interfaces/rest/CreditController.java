package com.example.finappapirest.finances.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/credits")
@Tag(name = "Credits", description = "Credits management")
public class CreditController {

    @GetMapping("/{creditId}")
    @Operation(summary = "Get credit by id", description = "Get credit by id in the system")
    public String getCredit(Long creditId) {
        return "Credit " + creditId;
    }

    @GetMapping("/{accountId}")
    @Operation(summary = "Get credits by account", description = "Get credits for an account by account id in the system")
    public String getCreditsByAccount(@PathVariable Long accountId) {
        return "Credits by account " + accountId;
    }

    @GetMapping("/{clientId}")
    @Operation(summary = "Get credits by client", description = "Get credits for a client by client id in the system")
    public String getCreditsByClient(@PathVariable Long clientId) {
        return "Credits by client " + clientId;
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "Get credits by store", description = "Get credits for a store by store id in the system")
    public String getCreditsByStore(@PathVariable Long storeId) {
        return "Credits by store " + storeId;
    }

    @PostMapping("/{accountId}")
    @Operation(summary = "Create credit", description = "Create a credit for an account in the system")
    public String createCredit(@PathVariable Long accountId) {
        return "Credit created for account " + accountId;
    }

    @PatchMapping("/change-status/{creditId}")
    @Operation(summary = "Change credit status", description = "Change credit status in the system")
    public String changeCreditStatus(@PathVariable Long creditId) {
        return "Credit status changed " + creditId;
    }
}
