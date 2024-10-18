package com.example.finappapirest.finances.interfaces.rest.transform;

import com.example.finappapirest.finances.domain.model.entities.Quota;
import com.example.finappapirest.finances.interfaces.rest.resources.response.credit.QuotaResponse;

import javax.swing.text.DateFormatter;
import java.util.Formatter;

public class QuotaResourceFromEntity {
    public static QuotaResponse fromEntity(Quota quota) {
        return new QuotaResponse(
                quota.getId(),
                quota.getTEA(),
                quota.getTEP(),
                quota.getGraceType(),
                quota.getOpeningBalance(),
                quota.getInterest(),
                quota.getAmortization(),
                quota.getQuotaToPay(),
                quota.getEndingBalance(),
                quota.getStatus(),
                quota.getDueDate().toString()
        );
    }
}
