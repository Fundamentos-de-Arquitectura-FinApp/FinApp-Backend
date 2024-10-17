package com.example.finappapirest.finances.application.internal.queryservices;

import com.example.finappapirest.finances.domain.model.aggregates.Credit;
import com.example.finappapirest.finances.domain.model.queries.credit.GetAllCreditsQuery;
import com.example.finappapirest.finances.domain.model.queries.credit.GetCreditByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.credit.GetCreditsByAccountIdQuery;
import com.example.finappapirest.finances.domain.model.queries.credit.GetCreditsByStoreQuery;
import com.example.finappapirest.finances.domain.services.queries.CreditQueryService;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.CreditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CreditQueryServiceImpl implements CreditQueryService {
    private final CreditRepository creditRepository;


    @Override
    public List<Credit> handle(GetAllCreditsQuery query) {
        return this.creditRepository.findAll();
    }

    @Override
    public Credit handle(GetCreditByIdQuery query) {
        return this.creditRepository.findById(query.creditId()).orElse(null);
    }

    @Override
    public List<Credit> handle(GetCreditsByAccountIdQuery query) {
        return this.creditRepository.findByAccount_Id(query.accountId());
    }

    @Override
    public List<Credit> handle(GetCreditsByStoreQuery query) {
        return this.creditRepository.findByAccount_Store_UserId(query.storeId());
    }
}
