package com.example.finappapirest.finances.interfaces.rest.resources.request.credit;

import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;

import java.time.LocalDate;

public record CreateOnePaymentRequest(
        Long accountId,
        Long orderId,
        Long initialPayment,
        CreateInterestRateRequest compensatoryRate,
        CreateInterestRateRequest moratoriumRate,
        LocalDate disbursementDate,
        LocalDate dueDate
) {
}
