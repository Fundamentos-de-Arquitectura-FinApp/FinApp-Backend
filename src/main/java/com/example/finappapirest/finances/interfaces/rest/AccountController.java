package com.example.finappapirest.finances.interfaces.rest;

import com.example.finappapirest.finances.domain.model.aggregates.Account;
import com.example.finappapirest.finances.domain.model.commands.account.CreateAccountCommand;
import com.example.finappapirest.finances.domain.model.commands.account.UpdateAccountCommand;
import com.example.finappapirest.finances.domain.model.queries.account.GetAccountByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.account.GetAccountsByStoreQuery;
import com.example.finappapirest.finances.domain.model.queries.account.GetAllAccountsQuery;
import com.example.finappapirest.finances.domain.services.commands.AccountCommandService;
import com.example.finappapirest.finances.domain.services.queries.AccountQueryService;
import com.example.finappapirest.finances.interfaces.rest.resources.request.CreateAccountRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.request.UpdateAccountRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.response.AccountResponse;
import com.example.finappapirest.finances.interfaces.rest.transform.AccountCommandFromResource;
import com.example.finappapirest.finances.interfaces.rest.transform.AccountResourceFromEntity;
import com.example.finappapirest.shared.interfaces.utils.UserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/accounts")
@Tag(name = "Accounts", description = "Accounts clients management")
@AllArgsConstructor
public class AccountController {

    private final AccountQueryService accountQueryService;
    private final AccountCommandService accountCommandService;

    @GetMapping()
    @Operation(summary = "Get all accounts", description = "Get all accounts from the system")
    public ResponseEntity<List<AccountResponse>> getAccounts() {
        GetAllAccountsQuery query = new GetAllAccountsQuery();
        List<Account> accounts = accountQueryService.handle(query);
        List<AccountResponse> response = accounts.stream().map(AccountResourceFromEntity::fromEntity).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/store")
    @Operation(summary = "Get all accounts by store", description = "Get all accounts by store")
    public ResponseEntity<List<AccountResponse>> getAccountsByStore() {
        var userId = UserUtils.getCurrentUserId();
        var query = new GetAccountsByStoreQuery(userId);
        List<Account> accounts = accountQueryService.handle(query);
        List<AccountResponse> response = accounts.stream().map(AccountResourceFromEntity::fromEntity).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountId}")
    @Operation(summary = "Get account by id", description = "Get account by id from the system")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable("accountId") Long id) {
        GetAccountByIdQuery query = new GetAccountByIdQuery(id);
        Account account = accountQueryService.handle(query);
        AccountResponse response = AccountResourceFromEntity.fromEntity(account);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{clientId}")
    @Operation(summary = "Open account", description = "Open account in the system")
    public ResponseEntity<AccountResponse> openAccount(@PathVariable("clientId") Long clientId, @RequestBody CreateAccountRequest request) {
        CreateAccountCommand command = AccountCommandFromResource.fromResource(request,clientId);
        Account account = accountCommandService.handle(command);
        AccountResponse response = AccountResourceFromEntity.fromEntity(account);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PutMapping("/{accountId}")
    @Operation(summary = "Update account", description = "Update account in the system")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable("accountId") Long accountId, @RequestBody UpdateAccountRequest request) {
        UpdateAccountCommand command = AccountCommandFromResource.fromResource(request, accountId);
        Account account = accountCommandService.handle(command);
        AccountResponse response = AccountResourceFromEntity.fromEntity(account);
        return ResponseEntity.ok(response);
    }

}
