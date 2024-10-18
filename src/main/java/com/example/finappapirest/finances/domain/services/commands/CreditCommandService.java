package com.example.finappapirest.finances.domain.services.commands;

import com.example.finappapirest.finances.domain.model.aggregates.Credit;
import com.example.finappapirest.finances.domain.model.commands.credit.CreateOnePaymentCreditCommand;
import com.example.finappapirest.finances.domain.model.commands.credit.CreateQuotaCreditCommand;
import com.example.finappapirest.finances.domain.model.commands.credit.PreviewOnePaymentCreditCommand;
import com.example.finappapirest.finances.domain.model.commands.credit.PreviewQuotaCreditCommand;
import com.example.finappapirest.finances.domain.model.entities.OnePaymentCredit;
import com.example.finappapirest.finances.domain.model.entities.QuotaCredit;

public interface CreditCommandService {
    OnePaymentCredit handle(CreateOnePaymentCreditCommand command);
    QuotaCredit handle(CreateQuotaCreditCommand command);
    OnePaymentCredit handle(PreviewOnePaymentCreditCommand command);
    QuotaCredit handle(PreviewQuotaCreditCommand command);
}
