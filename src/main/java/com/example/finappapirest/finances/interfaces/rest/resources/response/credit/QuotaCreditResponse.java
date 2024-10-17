package com.example.finappapirest.finances.interfaces.rest.resources.response.credit;

import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;

import java.time.LocalDate;

public class QuotaCreditResponse extends CreditResponse{
    public Long orderId;
    public Long initialPayment;
    public CreateInterestRateResponse compensatoryRate;
    public CreateInterestRateResponse moratoriumRate;
    public LocalDate disbursementDate;
    public Integer numQuotas;
    public PeriodType paymentPeriod;
    public CreateGraceResponse grace;
}
