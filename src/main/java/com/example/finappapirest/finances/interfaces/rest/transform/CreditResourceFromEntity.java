package com.example.finappapirest.finances.interfaces.rest.transform;

import com.example.finappapirest.finances.domain.model.aggregates.Credit;
import com.example.finappapirest.finances.domain.model.entities.OnePaymentCredit;
import com.example.finappapirest.finances.domain.model.entities.QuotaCredit;
import com.example.finappapirest.finances.interfaces.rest.resources.response.credit.CreditResponse;
import com.example.finappapirest.finances.interfaces.rest.resources.response.credit.OnePaymentCreditResponse;
import com.example.finappapirest.finances.interfaces.rest.resources.response.credit.QuotaCreditResponse;

public class CreditResourceFromEntity {
    public static CreditResponse toResource(Credit credit) {
        if(credit instanceof OnePaymentCredit) {
            return toResource((OnePaymentCredit) credit);
        } else {
            return toResource((QuotaCredit) credit);
        }
    }
    public static OnePaymentCreditResponse toResource(OnePaymentCredit onePaymentCredit) {
        OnePaymentCreditResponse response = new OnePaymentCreditResponse();
        response.orderId = onePaymentCredit.getOrderId();
        response.initialPayment = onePaymentCredit.getInitialPayment();
        response.totalAmount = onePaymentCredit.getAmount();
        response.dueDate = onePaymentCredit.getDueDate();
        response.compensatoryRate = InterestRateResourceFromEntity.toResponse(onePaymentCredit.getCompensatoryRate());
        response.moratoriumRate = InterestRateResourceFromEntity.toResponse(onePaymentCredit.getMoratoriumRate());
        response.disbursementDate = onePaymentCredit.getDisbursementDate();
        response.paymentPlan = PaymentPlanResourceFromEntity.toResponse(onePaymentCredit.getPaymentPlan());
        return response;
    }
    public static QuotaCreditResponse toResource(QuotaCredit quotaCredit) {
        QuotaCreditResponse response = new QuotaCreditResponse();
        response.orderId = quotaCredit.getOrderId();
        response.initialPayment = quotaCredit.getInitialPayment();
        response.totalAmount = quotaCredit.getAmount();
        response.compensatoryRate = InterestRateResourceFromEntity.toResponse(quotaCredit.getCompensatoryRate());
        response.moratoriumRate = InterestRateResourceFromEntity.toResponse(quotaCredit.getMoratoriumRate());
        response.disbursementDate = quotaCredit.getDisbursementDate();
        response.numQuotas = quotaCredit.getNumQuotas();
        response.paymentPeriod = quotaCredit.getPaymentPeriod();
        response.grace = GraceResourceFromEntity.fromEntity(quotaCredit.getGrace());
        response.paymentPlan = PaymentPlanResourceFromEntity.toResponse(quotaCredit.getPaymentPlan());
        return response;
    }
}
