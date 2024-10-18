package com.example.finappapirest.finances.domain.model.entities;

import com.example.finappapirest.finances.domain.model.aggregates.Credit;
import com.example.finappapirest.finances.domain.model.entities.grace.Grace;
import com.example.finappapirest.finances.domain.model.valueobjects.GraceType;
import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class QuotaCredit extends Credit {
    private Integer numQuotas;
    private PeriodType paymentPeriod;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Grace grace = null;

    @Override
    public PaymentPlan createPaymentPlan() {
        this.paymentPlan = new PaymentPlan();
        this.paymentPlan.createQuotas(this);
        return this.paymentPlan;
    }
}
