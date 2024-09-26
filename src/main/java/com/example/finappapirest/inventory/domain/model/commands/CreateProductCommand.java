package com.example.finappapirest.inventory.domain.model.commands;

public record CreateProductCommand(
        String name,
        String description,
        Float price,
        String imageUrl,
        Long storeId
) {
}
