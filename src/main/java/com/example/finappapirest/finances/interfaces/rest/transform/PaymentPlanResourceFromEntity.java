package com.example.finappapirest.finances.interfaces.rest.transform;

import com.example.finappapirest.finances.domain.model.entities.PaymentPlan;
import com.example.finappapirest.finances.interfaces.rest.resources.response.credit.PaymentPlanResponse;
import com.example.finappapirest.finances.interfaces.rest.resources.response.credit.QuotaResponse;

import java.util.List;

public class PaymentPlanResourceFromEntity {
    public static PaymentPlanResponse toResponse(PaymentPlan paymentPlan) {
        List<QuotaResponse> quotas = paymentPlan.getQuotas().stream()
                .map(QuotaResourceFromEntity::fromEntity)
                .toList();
        return new PaymentPlanResponse(
                paymentPlan.getId(),
                quotas
        );
    }
}
