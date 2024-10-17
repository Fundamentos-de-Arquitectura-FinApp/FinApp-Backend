package com.example.finappapirest.finances.application.internal.factories;

import com.example.finappapirest.finances.domain.model.commands.credit.CreateOnePaymentCreditCommand;
import com.example.finappapirest.finances.domain.model.commands.credit.CreateQuotaCreditCommand;
import com.example.finappapirest.finances.domain.model.commands.credit.PreviewOnePaymentCreditCommand;
import com.example.finappapirest.finances.domain.model.entities.OnePaymentCredit;
import com.example.finappapirest.finances.domain.model.entities.QuotaCredit;
import com.example.finappapirest.finances.domain.model.entities.grace.Grace;
import com.example.finappapirest.finances.domain.model.entities.rate.InterestRate;

public class CreditFactory {
    public static OnePaymentCredit create(CreateOnePaymentCreditCommand command, Float amount) {
        OnePaymentCredit credit = new OnePaymentCredit();
        credit.setAmount(amount);
        credit.setOrderId(command.orderId());
        credit.setInitialPayment(command.initialPayment());
        credit.setDisbursementDate(command.disbursementDate());
        credit.setCompensatoryRate(InterestRateFactory.create(command.compensatoryRate()));
        credit.setMoratoriumRate(InterestRateFactory.create(command.moratoriumRate()));
        return credit;

    }
    public static QuotaCredit create(CreateQuotaCreditCommand command, Float amount) {
        QuotaCredit credit = new QuotaCredit();
        credit.setAmount(amount);
        credit.setOrderId(command.orderId());
        credit.setInitialPayment(command.initialPayment());
        credit.setDisbursementDate(command.disbursementDate());
        credit.setCompensatoryRate(InterestRateFactory.create(command.compensatoryRate()));
        credit.setMoratoriumRate(InterestRateFactory.create(command.moratoriumRate()));
        credit.setNumQuotas(command.numQuotas());
        credit.setPaymentPeriod(command.paymentPeriod());

        Grace grace = new Grace();
        if(command.grace() != null) {
            grace.setNumQuotas(command.grace().numQuotas());
            grace.setGraceType(command.grace().type());
        }
        else{
            grace.setNumQuotas(0);
            grace.setGraceType(null);
        }

        credit.setGrace(grace);
        return credit;
    }
}
