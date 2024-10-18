package com.example.finappapirest.orders.interfaces.rest.resources.request;

import lombok.Data;

import java.util.List;


public record CreateOrderRequest(
        Long clientId,
        List<OrderItemRequest> items
) {
}
