package com.example.finappapirest.finances.domain.services.queries;

import com.example.finappapirest.finances.domain.model.entities.Quota;
import com.example.finappapirest.finances.domain.model.queries.quota.GetAllQuotasQuery;
import com.example.finappapirest.finances.domain.model.queries.quota.GetQuotaByIdQuery;
import com.example.finappapirest.finances.domain.model.queries.quota.GetQuotasByClientIdQuery;
import com.example.finappapirest.finances.domain.model.queries.quota.GetQuotasByStoreIdQuery;

import java.util.List;

public interface QuotaQueryService{
    List<Quota> handle(GetAllQuotasQuery query);
    List<Quota> handle(GetQuotasByStoreIdQuery query);
    List<Quota> handle(GetQuotasByClientIdQuery query);
    Quota handle(GetQuotaByIdQuery query);
}
