package com.example.finappapirest.finances.application.internal.commandservices;

import com.example.finappapirest.finances.domain.model.aggregates.Account;
import com.example.finappapirest.finances.domain.model.aggregates.Credit;
import com.example.finappapirest.finances.domain.model.commands.credit.CreateOnePaymentCreditCommand;
import com.example.finappapirest.finances.domain.model.commands.credit.CreateQuotaCreditCommand;
import com.example.finappapirest.finances.domain.model.commands.credit.PreviewOnePaymentCreditCommand;
import com.example.finappapirest.finances.domain.model.commands.credit.PreviewQuotaCreditCommand;
import com.example.finappapirest.finances.domain.model.entities.OnePaymentCredit;
import com.example.finappapirest.finances.domain.model.entities.QuotaCredit;
import com.example.finappapirest.finances.domain.services.commands.CreditCommandService;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.AccountRepository;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.CreditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreditCommandServiceImpl implements CreditCommandService {
    private final CreditRepository creditRepository;
    private final AccountRepository accountRepository;

    @Override
    public OnePaymentCredit handle(CreateOnePaymentCreditCommand command) {
        return null;
    }

    @Override
    public QuotaCredit handle(CreateQuotaCreditCommand command) {
        return null;
    }

    @Override
    public OnePaymentCredit handle(PreviewOnePaymentCreditCommand command) {
        return null;
    }

    @Override
    public QuotaCredit handle(PreviewQuotaCreditCommand command) {
        return null;
    }
}
