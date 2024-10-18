package com.example.finappapirest.finances.domain.model.commands.client;

public record CreateClientCommand(
        String email,
        String names,
        String paternalSurname,
        String maternalSurname,
        String dni,
        String phone,
        String photo
) {
}
