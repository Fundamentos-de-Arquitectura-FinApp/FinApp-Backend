package com.example.finappapirest.finances.domain.services.queries;

import com.example.finappapirest.finances.domain.model.aggregates.Account;
import com.example.finappapirest.finances.domain.model.queries.account.GetAccountByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.account.GetAccountsByStoreQuery;
import com.example.finappapirest.finances.domain.model.queries.account.GetAllAccountsQuery;

import java.util.List;

public interface AccountQueryService {
    List<Account> handle(GetAllAccountsQuery query);
    Account handle(GetAccountByIdQuery query);
    List<Account> handle(GetAccountsByStoreQuery query);
}
