package com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories;

import com.example.finappapirest.finances.domain.model.aggregates.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit,Long> {
    List<Credit> findByAccount_Id(Long accountId);
    List<Credit> findByAccount_Store_UserId(Long storeUserId);
}
