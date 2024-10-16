package com.example.finappapirest.finances.application.internal.commandservices;

import com.example.finappapirest.finances.domain.model.aggregates.Account;
import com.example.finappapirest.finances.domain.model.aggregates.Client;
import com.example.finappapirest.finances.domain.model.commands.account.CreateAccountCommand;
import com.example.finappapirest.finances.domain.model.commands.account.UpdateAccountCommand;
import com.example.finappapirest.finances.domain.model.queries.client.GetClientByIdQuery;
import com.example.finappapirest.finances.domain.services.commands.AccountCommandService;
import com.example.finappapirest.finances.domain.services.queries.ClientQueryService;
import com.example.finappapirest.finances.domain.services.queries.StoreQueryService;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.AccountRepository;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.ClientRepository;
import com.example.finappapirest.shared.domain.model.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountCommandServiceImpl implements AccountCommandService {

    private final ClientQueryService clientQueryService;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    @Override
    public Account handle(CreateAccountCommand command) {
        Client client = this.clientQueryService.handle(new GetClientByIdQuery(command.clientId()));
        if (client == null) {
            throw new BadRequestException("Client with id " + command.clientId() + " not found");
        }
        if(command.creditLine() < 0) {
            throw new BadRequestException("Credit line must be greater than 0");
        }
        if(command.creditLine()>1500) {
            throw new BadRequestException("Credit line must be less than S/1500");
        }
        client.openAccount(command.creditLine());
        this.clientRepository.save(client);
        return client.getAccount();
    }

    @Override
    public Account handle(UpdateAccountCommand command) {
        Account account = this.accountRepository.findById(command.accountId()).orElse(null);
        if (account == null) {
            throw new BadRequestException("Account with id " + command.accountId() + " not found");
        }
        if(command.creditLine() < 0) {
            throw new BadRequestException("Credit line must be greater than 0");
        }
        if(command.creditLine()>1500) {
            throw new BadRequestException("Credit line must be less than S/1500");
        }
        account.setCreditLine(command.creditLine());
        this.accountRepository.save(account);
        return account;
    }
}
