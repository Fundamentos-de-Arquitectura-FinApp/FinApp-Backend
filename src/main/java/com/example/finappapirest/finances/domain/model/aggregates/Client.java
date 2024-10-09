package com.example.finappapirest.finances.domain.model.aggregates;

import com.example.finappapirest.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Client extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String names;
    private String paternalSurname;
    private String maternalSurname;
    private String dni;
    private String phone;
    private String photo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Store store;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Account account;

    private Long userId;
    private boolean isActive = true;
}
