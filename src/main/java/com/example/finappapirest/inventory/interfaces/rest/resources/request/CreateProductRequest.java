package com.example.finappapirest.inventory.interfaces.rest.resources.request;

public record CreateProductRequest(
        String name,
        String description,
        Float price,
        String imageUrl
) {
}
