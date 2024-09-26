package com.example.finappapirest.inventory.domain.model.commands;

public record DeleteProductCommand(Long productId, Long storeId) {
}
