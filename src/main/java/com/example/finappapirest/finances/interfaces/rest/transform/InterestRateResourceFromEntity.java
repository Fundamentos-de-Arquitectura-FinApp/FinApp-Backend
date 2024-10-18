package com.example.finappapirest.finances.interfaces.rest.transform;

import com.example.finappapirest.finances.domain.model.entities.rate.EffectiveRate;
import com.example.finappapirest.finances.domain.model.entities.rate.InterestRate;
import com.example.finappapirest.finances.domain.model.entities.rate.NominalRate;
import com.example.finappapirest.finances.domain.model.valueobjects.RateType;
import com.example.finappapirest.finances.interfaces.rest.resources.response.credit.CreateInterestRateResponse;

public class InterestRateResourceFromEntity {
    public static CreateInterestRateResponse toResponse(InterestRate rate) {
        if (rate instanceof EffectiveRate effectiveRate) {
            return new CreateInterestRateResponse(
                    effectiveRate.getPeriodType(),
                    effectiveRate.getValue(),
                    RateType.EFFECTIVE
            );
        } else {
            NominalRate nominalRate = (NominalRate) rate;
            return new CreateInterestRateResponse(
                    nominalRate.getPeriodType(),
                    nominalRate.getValue(),
                    RateType.NOMINAL,
                    nominalRate.getCapitalizationPeriod()
            );
        }
    }
}
