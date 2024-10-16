package com.example.finappapirest.finances.application.internal.queryservices;

import com.example.finappapirest.finances.domain.model.aggregates.Account;
import com.example.finappapirest.finances.domain.model.queries.account.GetAccountByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.account.GetAccountsByStoreQuery;
import com.example.finappapirest.finances.domain.model.queries.account.GetAllAccountsQuery;
import com.example.finappapirest.finances.domain.services.queries.AccountQueryService;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountQueryServiceImpl implements AccountQueryService {

    private final AccountRepository accountRepository;

    @Override
    public List<Account> handle(GetAllAccountsQuery query) {
        return this.accountRepository.findAll();
    }

    @Override
    public Account handle(GetAccountByIdQuery query) {
        return this.accountRepository.findById(query.id()).orElse(null);
    }

    @Override
    public List<Account> handle(GetAccountsByStoreQuery query) {
        return this.accountRepository.findByStoreId(query.storeId());
    }
}
