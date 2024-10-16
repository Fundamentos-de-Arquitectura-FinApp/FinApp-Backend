package com.example.finappapirest.finances.interfaces.rest.transform;

import com.example.finappapirest.finances.domain.model.commands.account.CreateAccountCommand;
import com.example.finappapirest.finances.domain.model.commands.account.UpdateAccountCommand;
import com.example.finappapirest.finances.interfaces.rest.resources.request.CreateAccountRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.request.UpdateAccountRequest;

public class AccountCommandFromResource {
    public static CreateAccountCommand fromResource(CreateAccountRequest request, Long clientId) {
        return new CreateAccountCommand(
                clientId,
                request.creditLine()
        );
    }

    public static UpdateAccountCommand fromResource(UpdateAccountRequest request, Long accountId) {
        return new UpdateAccountCommand(
                accountId,
                request.creditLine()
        );
    }
}

