package com.example.finappapirest.finances.domain.model.entities;

import com.example.finappapirest.finances.domain.model.valueobjects.GraceType;
import com.example.finappapirest.finances.domain.model.valueobjects.QuotaStatus;
import com.example.finappapirest.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "quota")
public class Quota extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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


    @OneToOne(mappedBy = "quota", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Payment payment;
}
