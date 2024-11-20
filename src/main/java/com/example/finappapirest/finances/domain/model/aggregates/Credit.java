package com.example.finappapirest.finances.domain.model.aggregates;

import com.example.finappapirest.finances.domain.model.entities.PaymentPlan;
import com.example.finappapirest.finances.domain.model.entities.rate.InterestRate;
import com.example.finappapirest.finances.domain.model.valueobjects.CreditType;
import com.example.finappapirest.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Credit extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected boolean isActive = true;

    protected Long orderId;

    protected Float amount;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected InterestRate compensatoryRate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected InterestRate moratoriumRate;

    protected Float initialPayment;

    protected LocalDate disbursementDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    protected Account account;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="payment_plan_id")
    protected PaymentPlan paymentPlan;

    public abstract PaymentPlan createPaymentPlan();
}
