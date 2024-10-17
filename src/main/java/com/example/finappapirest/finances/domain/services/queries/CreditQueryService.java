package com.example.finappapirest.finances.domain.services.queries;

import com.example.finappapirest.finances.domain.model.aggregates.Credit;
import com.example.finappapirest.finances.domain.model.queries.credit.GetAllCreditsQuery;
import com.example.finappapirest.finances.domain.model.queries.credit.GetCreditByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.credit.GetCreditsByAccountIdQuery;
import com.example.finappapirest.finances.domain.model.queries.credit.GetCreditsByStoreQuery;

import java.util.List;

public interface CreditQueryService {
    List<Credit> handle(GetAllCreditsQuery query);
    Credit handle(GetCreditByIdQuery query);
    List<Credit> handle(GetCreditsByAccountIdQuery query);
    List<Credit> handle(GetCreditsByStoreQuery query);
}
