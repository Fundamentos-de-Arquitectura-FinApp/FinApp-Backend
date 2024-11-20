package com.example.finappapirest.finances.application.internal.scheduledservices;

import com.example.finappapirest.finances.domain.model.aggregates.Account;
import com.example.finappapirest.finances.domain.model.aggregates.Credit;
import com.example.finappapirest.finances.domain.model.entities.PaymentPlan;
import com.example.finappapirest.finances.domain.model.entities.Quota;
import com.example.finappapirest.finances.domain.model.queries.account.GetAllAccountsQuery;
import com.example.finappapirest.finances.domain.model.queries.credit.GetCreditsByAccountIdQuery;
import com.example.finappapirest.finances.domain.model.valueobjects.QuotaStatus;
import com.example.finappapirest.finances.domain.services.queries.AccountQueryService;
import com.example.finappapirest.finances.domain.services.queries.CreditQueryService;
import com.example.finappapirest.notifications.interfaces.acl.NotificationServiceFacade;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Component
@AllArgsConstructor
public class PaymentsNotificationsScheduled {
    private NotificationServiceFacade notificationServiceFacade;
    private AccountQueryService accountQueryService;
    private CreditQueryService creditQueryService;
    private Gson gson;

    @Scheduled(fixedRate = 5*1000*60)
    public void sendPaymentsNotifications() {
        GetAllAccountsQuery query = new GetAllAccountsQuery();
        List<Account> accounts = accountQueryService.handle(query);
        String messageBody;

        for (Account account : accounts) {
            Long StoreId = account.getStore().getUserId();
            GetCreditsByAccountIdQuery queryCredit = new GetCreditsByAccountIdQuery(account.getId());
            List<Credit> credits = creditQueryService.handle(queryCredit);
            System.out.println("Credits length: "+credits.size());
            for (Credit credit : credits) {
                PaymentPlan paymentPlan = credit.getPaymentPlan();
                List<Quota> quotas = paymentPlan.getQuotas();
                for (Quota quota : quotas) {
                    if(quota.getStatus()==QuotaStatus.OVERDUE){
                        messageBody = "title: Pago atrasado, message: El pago de la cuota "+quota.getNumber()+" del credito "+credit.getId()+" esta atrasado del cliente " +
                                account.getClient().getNames()+" con DNI "+account.getClient().getDni()+" de la tienda "+account.getStore().getName();

                        notificationServiceFacade.sendNotification(StoreId,messageBody);
                    }
                    if(quota.getStatus()== QuotaStatus.PENDING && LocalDate.now().isAfter(quota.getDueDate().minusDays(3))){
                        messageBody = "title: Pago proximo, message: El pago de la cuota "+quota.getNumber()+" del credito "+credit.getId()+" esta proximo del cliente " +
                                account.getClient().getNames()+" con DNI "+account.getClient().getDni()+" de la tienda "+account.getStore().getName();

                        notificationServiceFacade.sendNotification(StoreId,messageBody);
                    }
                }
            }
        }

    }
}
