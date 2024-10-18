package com.example.finappapirest.finances.application.internal.queryservices;

import com.example.finappapirest.finances.domain.model.entities.Quota;
import com.example.finappapirest.finances.domain.model.queries.quota.GetAllQuotasQuery;
import com.example.finappapirest.finances.domain.model.queries.quota.GetQuotaByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.quota.GetQuotasByClientIdQuery;
import com.example.finappapirest.finances.domain.model.queries.quota.GetQuotasByStoreIdQuery;
import com.example.finappapirest.finances.domain.services.queries.QuotaQueryService;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.QuotaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuotaQueryServiceImpl implements QuotaQueryService {

    private final QuotaRepository quotaRepository;

    @Override
    public List<Quota> handle(GetAllQuotasQuery query) {
        return quotaRepository.findAll();
    }

    @Override
    public List<Quota> handle(GetQuotasByStoreIdQuery query) {
        return quotaRepository.findByPaymentPlan_Credit_Account_Store_UserId(query.storeId());
    }

    @Override
    public List<Quota> handle(GetQuotasByClientIdQuery query) {
        return quotaRepository.findByPaymentPlan_Credit_Account_Client_Id(query.clientId());
    }

    @Override
    public Quota handle(GetQuotaByIdQuery query) {
        return quotaRepository.findById(query.quotaId()).orElse(null);
    }
}
