package com.example.finappapirest.finances.application.internal.commandservices;

import com.example.finappapirest.finances.application.internal.factories.CreditFactory;
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
import com.example.finappapirest.notifications.interfaces.acl.NotificationServiceFacade;
import com.example.finappapirest.orders.interfaces.acl.OrderServiceFacade;
import com.example.finappapirest.shared.domain.model.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreditCommandServiceImpl implements CreditCommandService {
    private final CreditRepository creditRepository;
    private final AccountRepository accountRepository;
    private final OrderServiceFacade orderServiceFacade;
    private final NotificationServiceFacade notificationServiceFacade;

    @Override
    public OnePaymentCredit handle(CreateOnePaymentCreditCommand command) {
        Account account = accountRepository.findById(command.accountId()).orElseThrow(
                () -> new BadRequestException("Account not found")
        );
        Double orderAmount = orderServiceFacade.getAmountByOrderId(command.orderId());
        OnePaymentCredit credit = CreditFactory.create(command, orderAmount.floatValue());

        credit.createPaymentPlan();
        credit.setAccount(account);

        notificationServiceFacade.sendNotification(account.getStore().getUserId(), "New credit created for client: " + account.getClient().getNames());
        notificationServiceFacade.sendNotification(account.getClient().getUserId(), "New credit created for you");
        return creditRepository.save(credit);

    }

    @Override
    public QuotaCredit handle(CreateQuotaCreditCommand command) {
        Account account = accountRepository.findById(command.accountId()).orElseThrow(
                () -> new BadRequestException("Account not found")
        );
        Double orderAmount = orderServiceFacade.getAmountByOrderId(command.orderId());

        if(orderAmount>account.getCreditLine())
            throw new BadRequestException("Credit line exceeded");

        QuotaCredit credit = CreditFactory.create(command, orderAmount.floatValue());

        credit.createPaymentPlan();
        credit.setAccount(account);

        notificationServiceFacade.sendNotification(account.getStore().getUserId(), "New credit created for client: " + account.getClient().getNames());
        notificationServiceFacade.sendNotification(account.getClient().getUserId(), "New credit created for you");
        return creditRepository.save(credit);
    }

    @Override
    public OnePaymentCredit handle(PreviewOnePaymentCreditCommand command) {
        Double orderAmount = orderServiceFacade.getAmountByOrderId(command.orderId());
        OnePaymentCredit credit = CreditFactory.create(command, orderAmount.floatValue());
        credit.createPaymentPlan();
        return credit;
    }

    @Override
    public QuotaCredit handle(PreviewQuotaCreditCommand command) {
        Double orderAmount = orderServiceFacade.getAmountByOrderId(command.orderId());
        QuotaCredit credit = CreditFactory.create(command, orderAmount.floatValue());
        credit.createPaymentPlan();
        System.out.println("orderAmount: " + orderAmount);

        return credit;
    }
}
