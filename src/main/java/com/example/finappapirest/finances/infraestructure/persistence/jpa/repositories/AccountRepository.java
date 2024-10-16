package com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories;

import com.example.finappapirest.finances.domain.model.aggregates.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findByStoreId(Long storeId);
}
