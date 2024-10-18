package com.example.finappapirest.finances.domain.model.aggregates;

import com.example.finappapirest.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ruc;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String photo;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Client> clients;

    private Long userId;

    public void addClient(Client client) {
        this.clients.add(client);
    }

}
