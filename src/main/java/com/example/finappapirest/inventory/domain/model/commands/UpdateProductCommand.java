package com.example.finappapirest.inventory.domain.model.commands;

public record UpdateProductCommand(
        Long productId,
        String name,
        String description,
        Float price,
        String imageUrl,
        Long storeId
) {
}
