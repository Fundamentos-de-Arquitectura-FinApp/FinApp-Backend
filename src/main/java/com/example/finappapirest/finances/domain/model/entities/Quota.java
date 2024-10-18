package com.example.finappapirest.finances.domain.model.entities;

import com.example.finappapirest.finances.domain.model.valueobjects.GraceType;
import com.example.finappapirest.finances.domain.model.valueobjects.QuotaStatus;
import com.example.finappapirest.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "quota")
@Getter
@Setter
public class Quota extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private Float TEA;
    private Float TEP;
    private GraceType graceType;
    private Float openingBalance;
    private Float interest;
    private Float amortization;
    private Float quotaToPay;
    private Float endingBalance;

    @Enumerated(EnumType.STRING)
    private QuotaStatus status;
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentPlan paymentPlan;

    @OneToOne(mappedBy = "quota", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Payment payment;

    public void calculateQuotaToPay(Quota lastQuota, int totalQuotas) {
        this.interest = openingBalance * TEP;
        if(this.graceType == GraceType.TOTAL){
            this.quotaToPay = 0f;
            this.endingBalance = this.openingBalance + this.interest;
            this.amortization = 0f;
        } else if (this.graceType == GraceType.PARTIAL) {
            this.quotaToPay = this.interest;
            this.amortization = 0f;
            this.endingBalance = this.openingBalance;
        } else {
            this.quotaToPay = (float) ((this.openingBalance*this.TEP)/(1-Math.pow(1+this.TEP, -(totalQuotas - (this.number-1)))));
            this.amortization = this.quotaToPay - this.interest;
            this.endingBalance = this.openingBalance - this.amortization;
        }
    }
}
