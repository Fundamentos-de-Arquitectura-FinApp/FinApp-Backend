package com.example.finappapirest.finances.interfaces.rest.resources.response.credit;

import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public record PaymentPlanResponse(
        Long paymentPlanId,
        List<QuotaResponse> quotas
) {
}
