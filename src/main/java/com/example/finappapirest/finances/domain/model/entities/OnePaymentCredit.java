package com.example.finappapirest.finances.domain.model.entities;

import com.example.finappapirest.finances.domain.model.aggregates.Credit;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class OnePaymentCredit extends Credit {
    @Override
    public PaymentPlan createPaymentPlan() {
        return null;
    }
}
