package com.example.finappapirest.finances.domain.services.commands;

import com.example.finappapirest.finances.domain.model.aggregates.Account;
import com.example.finappapirest.finances.domain.model.commands.account.CreateAccountCommand;
import com.example.finappapirest.finances.domain.model.commands.account.UpdateAccountCommand;

public interface AccountCommandService {
     Account handle(CreateAccountCommand command);
     Account handle(UpdateAccountCommand command);
}
