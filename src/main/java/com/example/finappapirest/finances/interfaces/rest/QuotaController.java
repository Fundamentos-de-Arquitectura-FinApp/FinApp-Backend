package com.example.finappapirest.finances.interfaces.rest;

import com.example.finappapirest.finances.domain.model.entities.Quota;
import com.example.finappapirest.finances.domain.model.queries.quota.GetAllQuotasQuery;
import com.example.finappapirest.finances.domain.model.queries.quota.GetQuotaByIdQuery;
import com.example.finappapirest.finances.domain.services.queries.QuotaQueryService;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.QuotaRepository;
import com.example.finappapirest.finances.interfaces.rest.resources.response.credit.QuotaResponse;
import com.example.finappapirest.finances.interfaces.rest.transform.QuotaResourceFromEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotas")
@Tag(name = "Quotas", description = "Quotas management")
@AllArgsConstructor
public class QuotaController {

    private final QuotaQueryService quotaQueryService;

    @GetMapping()
    @Operation(summary = "Get all quotas", description = "Get all quotas from the system")
    public ResponseEntity<List<QuotaResponse>> getAllQuotas() {
        GetAllQuotasQuery query = new GetAllQuotasQuery();
        List<Quota> quotas = quotaQueryService.handle(query);
        List<QuotaResponse> quotaResponse = quotas.stream().map(QuotaResourceFromEntity::fromEntity).toList();
        return ResponseEntity.ok(quotaResponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a quota", description = "Get a quota from the system")
    public ResponseEntity<QuotaResponse> getQuota(@PathVariable Long id) {
        GetQuotaByIdQuery query = new GetQuotaByIdQuery(id);
        Quota quota = quotaQueryService.handle(query);
        QuotaResponse quotaResponse = QuotaResourceFromEntity.fromEntity(quota);
        return ResponseEntity.ok(quotaResponse);
    }
}
