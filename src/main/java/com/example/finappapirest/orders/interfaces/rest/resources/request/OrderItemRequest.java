package com.example.finappapirest.orders.interfaces.rest.resources.request;

public record OrderItemRequest(
        Long productId,
        Integer quantity
) {
}
