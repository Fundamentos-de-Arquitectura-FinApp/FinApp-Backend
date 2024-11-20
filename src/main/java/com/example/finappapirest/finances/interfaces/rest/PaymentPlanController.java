package com.example.finappapirest.finances.interfaces.rest;

import com.example.finappapirest.finances.domain.model.entities.PaymentPlan;
import com.example.finappapirest.finances.domain.model.queries.paymentplan.GetAllPaymentPlan;
import com.example.finappapirest.finances.domain.model.queries.paymentplan.GetPaymentPlanByCreditId;
import com.example.finappapirest.finances.domain.model.queries.paymentplan.GetPaymentPlanById;
import com.example.finappapirest.finances.domain.services.queries.PaymentPlanQueryService;
import com.example.finappapirest.finances.infraestructure.persistence.jpa.repositories.PaymentPlanRepository;
import com.example.finappapirest.finances.interfaces.rest.resources.response.credit.PaymentPlanResponse;
import com.example.finappapirest.finances.interfaces.rest.transform.PaymentPlanResourceFromEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment-plan")
@Tag(name= "Payment Plan", description = "Payment Plan operations")
@AllArgsConstructor
public class PaymentPlanController {

    private final PaymentPlanQueryService paymentPlanQueryService;

    @GetMapping()
    @Operation(summary = "Get All Payments Plan", description = "Get All Payment Plan from the system")
    public ResponseEntity<List<PaymentPlanResponse>> getPaymentsPlan() {
        GetAllPaymentPlan query = new GetAllPaymentPlan();
        List<PaymentPlan> paymentPlans = paymentPlanQueryService.handle(query);
        List<PaymentPlanResponse> planResponses = paymentPlans.stream().map(PaymentPlanResourceFromEntity::toResponse).toList();
        return ResponseEntity.ok(planResponses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Payment Plan by Id", description = "Get Payment Plan by Id from the system")
    public ResponseEntity<PaymentPlanResponse> getPaymentPlanById(@PathVariable Long id) {
        GetPaymentPlanById query = new GetPaymentPlanById(id);
        PaymentPlan paymentPlan = paymentPlanQueryService.handle(query);
        if(paymentPlan == null) {
            return ResponseEntity.notFound().build();
        }
        PaymentPlanResponse planResponse = PaymentPlanResourceFromEntity.toResponse(paymentPlan);
        return ResponseEntity.ok(planResponse);
    }

    @GetMapping("/credit/{creditId}")
    @Operation(summary = "Get Payment Plan by Credit Id", description = "Get Payment Plan by Credit Id from the system")
    public ResponseEntity<PaymentPlanResponse> getPaymentPlanByCreditId(@PathVariable Long creditId) {
        GetPaymentPlanByCreditId query = new GetPaymentPlanByCreditId(creditId);
        PaymentPlan paymentPlan = paymentPlanQueryService.handle(query);
        if(paymentPlan == null) {
            return ResponseEntity.notFound().build();
        }
        PaymentPlanResponse planResponse = PaymentPlanResourceFromEntity.toResponse(paymentPlan);
        return ResponseEntity.ok(planResponse);
    }

}
