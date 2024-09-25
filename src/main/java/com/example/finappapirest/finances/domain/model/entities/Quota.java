package com.example.finappapirest.finances.domain.model.entities;

import com.example.finappapirest.finances.domain.model.valueobjects.QuotaStatus;
import com.example.finappapirest.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;

@Entity
@Table(name = "quota")
public class Quota extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float amount;

    @Enumerated(EnumType.STRING)
    private QuotaStatus status;

    @OneToOne(mappedBy = "quota", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Payment payment;
}
