package com.example.finappapirest.finances.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quotas")
@Tag(name = "Quotas", description = "Quotas management")
public class QuotaController {
    @GetMapping()
    @Operation(summary = "Get all quotas", description = "Get all quotas from the system")
    public String getAllQuotas() {
        return "All quotas";
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a quota", description = "Get a quota from the system")
    public String getQuota(@PathVariable String id) {
        return "A quota";
    }
}
