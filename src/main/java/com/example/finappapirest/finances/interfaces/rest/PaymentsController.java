package com.example.finappapirest.finances.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@Tag(name = "Payments", description = "Payments operations")
public class PaymentsController {
    @GetMapping()
    @Operation(summary = "Get all payments", description = "Get all payments from the system")
    public String getAllPayments() {
        return "All payments";
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a payment", description = "Get a payment from the system")
    public String getPayment(@PathVariable String id) {
        return "A payment";
    }

    @GetMapping("/store")
    @Operation(summary = "Get all payments registered in the store", description = "Get all payments registered in the store")
    public String getStorePayments() {
        return "All store payments";
    }

    @GetMapping("/{clientId}")
    @Operation(summary = "Get all client's payments", description = "Get all client's payments")
    public String getClientPayments(@PathVariable Long clientId) {
        return "All client's payments";
    }

    @PostMapping()
    @Operation(summary = "Create a payment", description = "Create a payment in the system")
    public String createPayment() {
        return "Payment created";
    }
}
