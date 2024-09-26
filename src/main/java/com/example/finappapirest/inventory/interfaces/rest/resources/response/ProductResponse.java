package com.example.finappapirest.inventory.interfaces.rest.resources.response;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Float price,
        String imageURL
) {
}
