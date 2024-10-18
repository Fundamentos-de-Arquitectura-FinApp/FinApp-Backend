package com.example.finappapirest.orders.interfaces.rest.resources.response;

public record ProductOrderResponse(
        Long id,
        String name,
        String description,
        String imageUrl
) {
}
