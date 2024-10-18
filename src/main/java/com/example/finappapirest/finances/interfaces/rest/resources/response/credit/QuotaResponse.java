package com.example.finappapirest.finances.interfaces.rest.resources.response.credit;

import com.example.finappapirest.finances.domain.model.valueobjects.GraceType;
import com.example.finappapirest.finances.domain.model.valueobjects.QuotaStatus;

public record QuotaResponse(
        Long id,
        Float TEA,
        Float TEP,
        GraceType graceType,
        Float openingBalance,
        Float interest,
        Float amortization,
        Float quotaToPay,
        Float endingBalance,
        QuotaStatus status,
        String dueDate
) {
}
