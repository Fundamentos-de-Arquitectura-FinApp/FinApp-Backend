package com.example.finappapirest.finances.domain.model.commands.store;

public record UpdateStoreCommand(
        Long id,
        String ruc,
        String name,
        String phone,
        String address,
        String photo
) {
}
