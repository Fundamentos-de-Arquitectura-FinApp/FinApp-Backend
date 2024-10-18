package com.example.finappapirest.finances.interfaces.rest;

import com.example.finappapirest.finances.domain.model.aggregates.Credit;
import com.example.finappapirest.finances.domain.model.commands.credit.CreateOnePaymentCreditCommand;
import com.example.finappapirest.finances.domain.model.commands.credit.CreateQuotaCreditCommand;
import com.example.finappapirest.finances.domain.model.commands.credit.PreviewOnePaymentCreditCommand;
import com.example.finappapirest.finances.domain.model.commands.credit.PreviewQuotaCreditCommand;
import com.example.finappapirest.finances.domain.model.entities.OnePaymentCredit;
import com.example.finappapirest.finances.domain.model.entities.QuotaCredit;
import com.example.finappapirest.finances.domain.model.queries.credit.GetAllCreditsQuery;
import com.example.finappapirest.finances.domain.model.queries.credit.GetCreditByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.credit.GetCreditsByAccountIdQuery;
import com.example.finappapirest.finances.domain.model.queries.credit.GetCreditsByStoreQuery;
import com.example.finappapirest.finances.domain.services.commands.CreditCommandService;
import com.example.finappapirest.finances.domain.services.queries.CreditQueryService;
import com.example.finappapirest.finances.interfaces.rest.resources.request.credit.CreateOnePaymentRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.request.credit.CreateQuotaCreditRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.request.credit.PreviewOnePaymentRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.request.credit.PreviewQuotaCreditRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.response.credit.OnePaymentCreditResponse;
import com.example.finappapirest.finances.interfaces.rest.resources.response.credit.QuotaCreditResponse;
import com.example.finappapirest.finances.interfaces.rest.resources.response.credit.CreditResponse;
import com.example.finappapirest.finances.interfaces.rest.transform.CreditCommandFromResource;
import com.example.finappapirest.finances.interfaces.rest.transform.CreditResourceFromEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/credits")
@Tag(name = "Credits", description = "Credits management")
@AllArgsConstructor
public class CreditController {

    private final CreditQueryService creditQueryService;
    private final CreditCommandService creditCommandService;

    @GetMapping()
    @Operation(summary = "Get all credits", description = "Get all credits in the system")
    public ResponseEntity<List<CreditResponse>> getAllCredits() {
        GetAllCreditsQuery query = new GetAllCreditsQuery();
        List<Credit> credits = creditQueryService.handle(query);
        List<CreditResponse> creditResponses = credits.stream().map(CreditResourceFromEntity::toResource).toList();
        return ResponseEntity.ok(creditResponses);
    }

    @GetMapping("/{creditId}")
    @Operation(summary = "Get credit by id", description = "Get credit by id in the system")
    public ResponseEntity<CreditResponse> getCredit(@PathVariable Long creditId) {
        GetCreditByIdQuery query = new GetCreditByIdQuery(creditId);
        Credit credit = creditQueryService.handle(query);
        CreditResponse creditResponse = CreditResourceFromEntity.toResource(credit);
        return ResponseEntity.ok(creditResponse);
    }

    @GetMapping("/{accountId}")
    @Operation(summary = "Get credits by account", description = "Get credits for an account by account id in the system")
    public ResponseEntity<List<CreditResponse>> getCreditsByAccount(@PathVariable Long accountId) {
        GetCreditsByAccountIdQuery query = new GetCreditsByAccountIdQuery(accountId);
        List<Credit> credits = creditQueryService.handle(query);
        List<CreditResponse> creditResponses = credits.stream().map(CreditResourceFromEntity::toResource).toList();
        return ResponseEntity.ok(creditResponses);
    }


    @GetMapping("/{storeId}")
    @Operation(summary = "Get credits by store", description = "Get credits for a store by store id in the system")
    public ResponseEntity<List<CreditResponse>> getCreditsByStore(@PathVariable Long storeId) {
        GetCreditsByStoreQuery query = new GetCreditsByStoreQuery(storeId);
        List<Credit> credits = creditQueryService.handle(query);
        List<CreditResponse> creditResponses = credits.stream().map(CreditResourceFromEntity::toResource).toList();
        return ResponseEntity.ok(creditResponses);
    }

    @PostMapping("/one-payment-credit/{accountId}")
    @Operation(summary = "Create credit", description = "Create a credit for an account in the system")
    public ResponseEntity<OnePaymentCreditResponse> createOnePaymentCredit(@PathVariable Long accountId, @RequestBody CreateOnePaymentRequest creditRequest) {
        CreateOnePaymentCreditCommand command = CreditCommandFromResource.fromResource(creditRequest);
        OnePaymentCredit credit = creditCommandService.handle(command);
        OnePaymentCreditResponse creditResponse = CreditResourceFromEntity.toResource(credit);
        return new ResponseEntity<>(creditResponse, HttpStatus.CREATED);
    }

    @PostMapping("/quota-credit/{accountId}")
    @Operation(summary = "Create credit", description = "Create a credit for an account in the system")
    public ResponseEntity<QuotaCreditResponse> createQuotaCredit(@PathVariable Long accountId, @RequestBody CreateQuotaCreditRequest creditRequest) {
        CreateQuotaCreditCommand command = CreditCommandFromResource.fromResource(creditRequest);
        QuotaCredit credit = creditCommandService.handle(command);
        QuotaCreditResponse creditResponse = CreditResourceFromEntity.toResource(credit);
        return new ResponseEntity<>(creditResponse, HttpStatus.CREATED);
    }

    @PostMapping("/one-payment-credit/preview")
    @Operation(summary = "Preview credit", description = "Preview credit and payment plan generated by the system")
    public ResponseEntity<OnePaymentCreditResponse> previewCredit(@RequestBody PreviewOnePaymentRequest request) {
        PreviewOnePaymentCreditCommand command = CreditCommandFromResource.fromResource(request);
        OnePaymentCredit credit = creditCommandService.handle(command);
        OnePaymentCreditResponse creditResponse = CreditResourceFromEntity.toResource(credit);
        return new ResponseEntity<>(creditResponse, HttpStatus.CREATED);
    }
    @PostMapping("/quota-credit/preview")
    @Operation(summary = "Preview credit", description = "Preview credit and payment plan generated by the system")
    public ResponseEntity<QuotaCreditResponse> previewCredit(@RequestBody PreviewQuotaCreditRequest creditRequest) {
        PreviewQuotaCreditCommand command = CreditCommandFromResource.fromResource(creditRequest);
        QuotaCredit credit = creditCommandService.handle(command);
        QuotaCreditResponse creditResponse = CreditResourceFromEntity.toResource(credit);
        return new ResponseEntity<>(creditResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/change-status/{creditId}")
    @Operation(summary = "Change credit status", description = "Change credit status in the system")
    public String changeCreditStatus(@PathVariable Long creditId) {
        return "Credit status changed " + creditId;
    }
}
