package com.example.finappapirest.finances.domain.model.commands.account;

public record CreateAccountCommand(
        Long clientId,
        Float creditLine
) {
}
