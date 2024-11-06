package com.example.finappapirest.finances.domain.model.entities;

import com.example.finappapirest.finances.application.internal.strategyservices.InterestRateContext;
import com.example.finappapirest.finances.domain.model.aggregates.Credit;
import com.example.finappapirest.finances.domain.model.valueobjects.GraceType;
import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;
import com.example.finappapirest.finances.domain.model.valueobjects.QuotaStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "payment_plan")
public class PaymentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Quota> quotas = new ArrayList<>();

    @OneToOne(mappedBy = "paymentPlan")
    private Credit credit;

    public void createQuotas(Credit credit) {
        if(credit instanceof QuotaCredit) {
            createForQuotaCredit((QuotaCredit) credit);
        } else if(credit instanceof OnePaymentCredit) {
            createForOnePayment((OnePaymentCredit) credit);
        }
    }
    public void createForOnePayment(OnePaymentCredit onePaymentCredit) {
        InterestRateContext interestRateContext = new InterestRateContext();

        Quota quota = new Quota();

        int daysOfCredit = numberDaysBetweenDates(onePaymentCredit.getDisbursementDate(), onePaymentCredit.getDueDate());
        double TEA = interestRateContext.convertRate(onePaymentCredit.getCompensatoryRate(), PeriodType.ANNUALLY.getValue());
        double TEP = interestRateContext.convertRate(onePaymentCredit.getCompensatoryRate(), daysOfCredit);
        GraceType graceType = GraceType.NONE;
        double openingBalance = onePaymentCredit.getAmount();
        double quotaToPay = openingBalance * TEP;
        float amortization = onePaymentCredit.getAmount();
        float interest = (float) (quotaToPay - amortization);
        float endingBalance = 0;
        QuotaStatus status = QuotaStatus.PENDING;
        LocalDate dueDate = onePaymentCredit.getDueDate();

        quota.setTEA((float) TEA);
        quota.setTEP((float) TEP);
        quota.setGraceType(graceType);
        quota.setOpeningBalance((float) openingBalance);
        quota.setInterest(interest);
        quota.setAmortization(amortization);
        quota.setQuotaToPay((float)quotaToPay);
        quota.setEndingBalance(endingBalance);
        quota.setStatus(status);
        quota.setDueDate(dueDate);
        quota.setNumber(1);
        quotas.add(quota);
    }
    public void createForQuotaCredit(QuotaCredit quotaCredit) {
        InterestRateContext interestRateContext = new InterestRateContext();


        double TEA = interestRateContext.convertRate(quotaCredit.getCompensatoryRate(), PeriodType.ANNUALLY.getValue());
        System.out.println("TEA: " + TEA);

        double TEP = interestRateContext.convertRate(quotaCredit.getCompensatoryRate(), quotaCredit.getPaymentPeriod().getValue());
        int numQuotas = quotaCredit.getNumQuotas();
        int numGracePeriods = quotaCredit.getGrace().getNumQuotas();
        GraceType graceType = quotaCredit.getGrace().getGraceType();



        Quota quotaZero = new Quota();
        quotaZero.setNumber(0);
        quotaZero.setTEA((float) TEA);
        quotaZero.setTEP((float) TEP);
        quotaZero.setGraceType(GraceType.NONE);
        quotaZero.setOpeningBalance(quotaCredit.getAmount());
        quotaZero.setInterest(0f);
        quotaZero.setAmortization(0f);
        quotaZero.setQuotaToPay(0f);
        quotaZero.setEndingBalance(quotaCredit.getAmount());
        quotaZero.setStatus(null);

        quotaZero.setDueDate(quotaCredit.getDisbursementDate());

        Quota quotaAux = quotaZero;
        for (int i = 1; i<= numQuotas; i++) {
               Quota quota = new Quota();
                quota.setNumber(i);
                quota.setTEA((float) TEA);
                quota.setTEP((float) TEP);
                quota.setGraceType(GraceType.NONE);
                if(i<=numGracePeriods){
                    quota.setGraceType(graceType);
                }
                quota.setOpeningBalance(quotaAux.getEndingBalance());
                quota.setDueDate(quotaAux.getDueDate().plusDays(quotaCredit.getPaymentPeriod().getValue()));
                quota.calculateQuotaToPay(quotaAux, numQuotas);
                quotaAux = quota;
                quotas.add(quota);
        }
    }
    private int numberDaysBetweenDates(LocalDate startDate, LocalDate endDate) {
        long numberDays = ChronoUnit.DAYS.between(startDate, endDate);
        return (int) numberDays;
    }
}
