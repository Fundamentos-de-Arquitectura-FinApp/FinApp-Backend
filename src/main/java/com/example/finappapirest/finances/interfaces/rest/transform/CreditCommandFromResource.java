package com.example.finappapirest.finances.interfaces.rest.transform;

import com.example.finappapirest.finances.domain.model.commands.credit.CreateOnePaymentCreditCommand;
import com.example.finappapirest.finances.domain.model.commands.credit.CreateQuotaCreditCommand;
import com.example.finappapirest.finances.domain.model.commands.credit.PreviewOnePaymentCreditCommand;
import com.example.finappapirest.finances.domain.model.commands.credit.PreviewQuotaCreditCommand;
import com.example.finappapirest.finances.interfaces.rest.resources.request.credit.CreateOnePaymentRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.request.credit.CreateQuotaCreditRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.request.credit.PreviewOnePaymentRequest;
import com.example.finappapirest.finances.interfaces.rest.resources.request.credit.PreviewQuotaCreditRequest;

public class CreditCommandFromResource {
    public static CreateQuotaCreditCommand fromResource(CreateQuotaCreditRequest request) {
        return new CreateQuotaCreditCommand(
                request.accountId(),
                request.orderId(),
                request.initialPayment(),
                request.compensatoryRate(),
                request.moratoriumRate(),
                request.disbursementDate(),
                request.numQuotas(),
                request.paymentPeriod(),
                request.grace()
        );
    }

    public static CreateOnePaymentCreditCommand fromResource(CreateOnePaymentRequest request) {
        return new CreateOnePaymentCreditCommand(
                request.accountId(),
                request.orderId(),
                request.initialPayment(),
                request.compensatoryRate(),
                request.moratoriumRate(),
                request.disbursementDate(),
                request.dueDate()
        );
    }

    public static PreviewQuotaCreditCommand fromResource(PreviewQuotaCreditRequest request) {
        return new PreviewQuotaCreditCommand(
                request.orderId(),
                request.initialPayment(),
                request.compensatoryRate(),
                request.moratoriumRate(),
                request.disbursementDate(),
                request.numQuotas(),
                request.paymentPeriod(),
                request.grace()
        );
    }

    public static PreviewOnePaymentCreditCommand fromResource(PreviewOnePaymentRequest request) {
        return new PreviewOnePaymentCreditCommand(
                request.orderId(),
                request.initialPayment(),
                request.compensatoryRate(),
                request.moratoriumRate(),
                request.disbursementDate(),
                request.dueDate()
        );
    }
}
