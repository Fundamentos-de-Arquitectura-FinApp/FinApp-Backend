package com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories;

import com.example.finappapirest.finances.domain.model.aggregates.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByDni(String dni);
    List<Client> findByStoreId(Long storeId);
}
