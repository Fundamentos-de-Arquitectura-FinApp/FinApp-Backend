package com.example.finappapirest.finances.domain.model.entities;

import com.example.finappapirest.finances.domain.model.aggregates.Credit;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class OnePaymentCredit extends Credit {
    private LocalDate dueDate;

    @Override
    public PaymentPlan createPaymentPlan() {
        return null;
    }
}
