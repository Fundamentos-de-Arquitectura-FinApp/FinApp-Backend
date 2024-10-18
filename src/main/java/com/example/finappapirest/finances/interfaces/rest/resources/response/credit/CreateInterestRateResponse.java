package com.example.finappapirest.finances.interfaces.rest.resources.response.credit;

import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;
import com.example.finappapirest.finances.domain.model.valueobjects.RateType;

public record CreateInterestRateResponse(
        PeriodType period,
        Float rate,
        RateType rateType,
        PeriodType capitalizationPeriod
) {
    public CreateInterestRateResponse {
       if(rateType==RateType.EFFECTIVE)
           capitalizationPeriod = null;
    }
    public CreateInterestRateResponse(PeriodType period, Float rate, RateType rateType) {
        this(period, rate,rateType, null);
    }
}
