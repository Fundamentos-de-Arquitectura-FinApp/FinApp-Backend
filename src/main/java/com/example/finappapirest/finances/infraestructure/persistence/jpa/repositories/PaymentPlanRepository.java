package com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories;

import com.example.finappapirest.finances.domain.model.entities.PaymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentPlanRepository extends JpaRepository<PaymentPlan, Long> {

}
