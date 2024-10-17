package com.example.finappapirest.finances.domain.model.commands.credit;

import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;
import com.example.finappapirest.finances.interfaces.rest.resources.request.credit.CreateGraceRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.request.credit.CreateInterestRateRequest;

import java.time.LocalDate;

public record CreateQuotaCreditCommand(
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
