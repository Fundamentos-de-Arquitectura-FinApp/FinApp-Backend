package com.example.finappapirest.finances.application.internal.queryservices;

import com.example.finappapirest.finances.domain.model.entities.PaymentPlan;
import com.example.finappapirest.finances.domain.model.queries.paymentplan.GetAllPaymentPlan;
import com.example.finappapirest.finances.domain.model.queries.paymentplan.GetPaymentPlanByCreditId;
import com.example.finappapirest.finances.domain.model.queries.paymentplan.GetPaymentPlanById;
import com.example.finappapirest.finances.domain.services.queries.PaymentPlanQueryService;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.PaymentPlanRepository;
import com.example.finappapirest.shared.domain.model.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentPlanQueryServiceImpl implements PaymentPlanQueryService {

    private final PaymentPlanRepository paymentPlanRepository;

    @Override
    public PaymentPlan handle(GetPaymentPlanById query) {
        return paymentPlanRepository.findById(query.paymentPlanId()).orElseThrow( () -> new NotFoundException("Payment Plan not found"));
    }

    @Override
    public List<PaymentPlan> handle(GetAllPaymentPlan query) {
        return paymentPlanRepository.findAll();
    }

    @Override
    public PaymentPlan handle(GetPaymentPlanByCreditId query) {
        return paymentPlanRepository.findByCredit_Id(query.creditId());
    }
}
