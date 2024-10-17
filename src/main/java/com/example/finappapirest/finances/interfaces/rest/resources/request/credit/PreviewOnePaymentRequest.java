package com.example.finappapirest.finances.interfaces.rest.resources.request.credit;

import java.time.LocalDate;

public record PreviewOnePaymentRequest(
        Long orderId,
        Long initialPayment,
        CreateInterestRateRequest compensatoryRate,
        CreateInterestRateRequest moratoriumRate,
        LocalDate disbursementDate,
        LocalDate dueDate
) {
}
