package com.example.finappapirest.finances.domain.model.aggregates;

import com.example.finappapirest.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;

@Entity
public class Client extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String names;
    private String paternSurname;
    private String maternSurname;
    private String dni;
    private String phone;
    private String photo;

    @ManyToOne
    private Store store;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Account account;

    private Long userId;
}
