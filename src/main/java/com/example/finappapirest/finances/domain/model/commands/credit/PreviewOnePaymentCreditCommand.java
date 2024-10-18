package com.example.finappapirest.finances.domain.model.commands.credit;

import com.example.finappapirest.finances.interfaces.rest.resources.request.credit.CreateInterestRateRequest;

import java.time.LocalDate;

public record PreviewOnePaymentCreditCommand(
        Long orderId,
        Float initialPayment,
        CreateInterestRateRequest compensatoryRate,
        CreateInterestRateRequest moratoriumRate,
        LocalDate disbursementDate,
        LocalDate dueDate
) {
}
