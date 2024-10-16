package com.example.finappapirest.finances.interfaces.rest.transform;

import com.example.finappapirest.finances.domain.model.aggregates.Account;
import com.example.finappapirest.finances.interfaces.rest.resources.response.AccountResponse;

public class AccountResourceFromEntity {
    public static AccountResponse fromEntity(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getCreditLine(),
                account.getClient().getDni(),
                account.getStore().getName(),
                account.getStore().getRuc()
        );
    }
}

