package com.example.finappapirest.finances.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment-plan")
@Tag(name= "Payment Plan", description = "Payment Plan operations")
public class PaymentPlanController {
    @GetMapping()
    @Operation(summary = "Get All Payments Plan", description = "Get All Payment Plan from the system")
    public String getPaymentsPlan() {
        return "Hello Payment Plan";
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Payment Plan by Id", description = "Get Payment Plan by Id from the system")
    public String getPaymentPlanById(@PathVariable String id) {
        return "Hello Payment Plan by Id";
    }

    @GetMapping("/{creditId}")
    @Operation(summary = "Get Payment Plan by Credit Id", description = "Get Payment Plan by Credit Id from the system")
    public String getPaymentPlanByCreditId(@PathVariable String creditId) {
        return "Hello Payment Plan by Credit Id";
    }

}
