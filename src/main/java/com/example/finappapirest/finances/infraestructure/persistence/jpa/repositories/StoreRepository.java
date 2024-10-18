package com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories;

import com.example.finappapirest.finances.domain.model.aggregates.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    public Optional<Store> findByUserId(Long userId);
}
