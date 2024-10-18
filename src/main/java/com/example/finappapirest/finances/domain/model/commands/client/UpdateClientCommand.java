package com.example.finappapirest.finances.domain.model.commands.client;

public record UpdateClientCommand(
        Long id,
        String phone,
        String photo
) {
}
