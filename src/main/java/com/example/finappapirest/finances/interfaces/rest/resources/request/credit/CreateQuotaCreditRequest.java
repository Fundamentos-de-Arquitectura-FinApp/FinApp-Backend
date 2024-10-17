package com.example.finappapirest.finances.interfaces.rest.resources.request.credit;

import com.example.finappapirest.finances.domain.model.valueobjects.GraceType;
import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;

import java.time.LocalDate;

public record CreateQuotaCreditRequest(
        Long accountId,
        Long orderId,
        Long initialPayment,
        CreateInterestRateRequest compensatoryRate,
        CreateInterestRateRequest moratoriumRate,
        LocalDate disbursementDate,
        Integer numQuotas,
        PeriodType paymentPeriod,
        CreateGraceRequest grace
) {
}
