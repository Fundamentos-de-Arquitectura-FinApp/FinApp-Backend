package com.example.finappapirest.finances.domain.services.queries;

import com.example.finappapirest.finances.domain.model.entities.PaymentPlan;
import com.example.finappapirest.finances.domain.model.queries.paymentplan.GetAllPaymentPlan;
import com.example.finappapirest.finances.domain.model.queries.paymentplan.GetPaymentPlanByCreditId;
import com.example.finappapirest.finances.domain.model.queries.paymentplan.GetPaymentPlanById;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PaymentPlanQueryService{
    PaymentPlan handle(GetPaymentPlanById query);
    List<PaymentPlan> handle(GetAllPaymentPlan query);
    PaymentPlan handle(GetPaymentPlanByCreditId query);
}
