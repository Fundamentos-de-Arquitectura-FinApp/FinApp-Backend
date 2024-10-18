package com.example.finappapirest.finances.interfaces.rest.resources.request.credit;

import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;

import java.time.LocalDate;

public record PreviewQuotaCreditRequest(
        Long orderId,
        Float initialPayment,
        CreateInterestRateRequest compensatoryRate,
        CreateInterestRateRequest moratoriumRate,
        LocalDate disbursementDate,
        Integer numQuotas,
        PeriodType paymentPeriod,
        CreateGraceRequest grace
) {
}
