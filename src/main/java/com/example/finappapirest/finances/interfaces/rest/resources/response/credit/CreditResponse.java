package com.example.finappapirest.finances.interfaces.rest.resources.response.credit;

import java.time.LocalDate;

public class CreditResponse {
    public Long creditId;
    public Long orderId;
    public Long initialPayment;
    public Float totalAmount;
    public CreateInterestRateResponse compensatoryRate;
    public CreateInterestRateResponse moratoriumRate;
    public LocalDate disbursementDate;
    public PaymentPlanResponse paymentPlan;
}
