package com.example.finappapirest.finances.interfaces.rest.resources.request.credit;

import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;
import com.example.finappapirest.finances.domain.model.valueobjects.RateType;

public record CreateInterestRateRequest(
        PeriodType period,
        Float rate,
        RateType rateType,
        PeriodType capitalizationPeriod
) {
    public CreateInterestRateRequest {
       if(rateType==RateType.EFFECTIVE)
           capitalizationPeriod = null;
    }
}
