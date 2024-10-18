package com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories;

import com.example.finappapirest.finances.domain.model.entities.Quota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotaRepository extends JpaRepository<Quota,Long> {
    List<Quota> findByPaymentPlan_Credit_Account_Store_UserId(Long userId);
    List<Quota> findByPaymentPlan_Credit_Account_Client_Id(Long clientId);
}
