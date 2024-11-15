package com.example.finappapirest.finances.interfaces.rest;

import com.example.finappapirest.finances.domain.model.valueobjects.CreditType;
import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;
import com.example.finappapirest.finances.domain.model.valueobjects.RateType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping("/api/v1/enums")
@Tag(name = "Enums", description = "Enums management")
public class EnumsController {
    @GetMapping("/rate-types")
    @Operation(summary = "Get credit types", description = "Get all credit types")
    public ResponseEntity<List<String>> getRateTypes() {
        return ResponseEntity.ok(Arrays.stream(RateType.values()).map(Enum::name).toList());
    }

    @GetMapping("/period-types")
    @Operation(summary = "Get period types", description = "Get all period types")
    public ResponseEntity<List<String>> getPeriodTypes() {
        return ResponseEntity.ok(Arrays.stream(PeriodType.values()).map(Enum::name).toList());
    }

}
