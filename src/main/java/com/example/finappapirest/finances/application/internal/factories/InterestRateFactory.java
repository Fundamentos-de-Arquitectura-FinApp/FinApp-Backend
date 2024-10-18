package com.example.finappapirest.finances.application.internal.factories;

import com.example.finappapirest.finances.domain.model.entities.rate.EffectiveRate;
import com.example.finappapirest.finances.domain.model.entities.rate.InterestRate;
import com.example.finappapirest.finances.domain.model.entities.rate.NominalRate;
import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;
import com.example.finappapirest.finances.domain.model.valueobjects.RateType;
import com.example.finappapirest.finances.interfaces.rest.resources.request.credit.CreateInterestRateRequest;

public class InterestRateFactory {
    public static InterestRate create(CreateInterestRateRequest request) {
        if(request.rateType() == RateType.EFFECTIVE) {
            return new EffectiveRate(request.rate(), request.period());
        } else {
            return new NominalRate(request.rate(), request.period(),request.capitalizationPeriod());
        }
    }
}
