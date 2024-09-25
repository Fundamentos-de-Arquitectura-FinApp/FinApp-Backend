package com.example.finappapirest.orders.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/orders")
@Tag(name = "Orders", description = "Orders management")
public class OrdersController {
    @GetMapping()
    @Operation(summary = "Get all orders", description = "Get all orders")
    public String getOrders() {
        return "Orders";
    }

    @GetMapping("/store")
    @Operation(summary = "Get orders by store", description = "Get orders by store")
    public String getOrdersByStore() {
        return "Orders by store";
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get order", description = "Get order by id")
    public String getOrder(@PathVariable Long orderId) {
        return "Order " + orderId;
    }

    @GetMapping("/{clientId}")
    @Operation(summary = "Get orders by client", description = "Get orders by client")
    public String getOrdersByClient(@PathVariable Long clientId) {
        return "Orders by client " + clientId;
    }

    @PostMapping("/preview")
    @Operation(summary = "Preview order", description = "Preview order before creating it")
    public String previewOrder() {
        return "Order preview";
    }

    @PostMapping()
    @Operation(summary = "Create order", description = "Create order in the system and return the order id")
    public String createOrder() {
        return "Order created";
    }
}
