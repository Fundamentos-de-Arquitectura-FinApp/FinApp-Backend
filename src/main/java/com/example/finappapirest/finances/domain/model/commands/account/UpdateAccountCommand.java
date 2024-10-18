package com.example.finappapirest.finances.domain.model.commands.account;

public record UpdateAccountCommand(
        Long accountId,
        Float creditLine
) {
}
