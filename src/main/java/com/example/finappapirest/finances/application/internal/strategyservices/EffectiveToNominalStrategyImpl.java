package com.example.finappapirest.finances.application.internal.strategyservices;

import com.example.finappapirest.finances.domain.model.entities.rate.EffectiveRate;
import com.example.finappapirest.finances.domain.model.entities.rate.InterestRate;
import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;
import com.example.finappapirest.finances.domain.services.strategies.InterestRateStrategy;
import com.example.finappapirest.shared.domain.model.exceptions.InternalServerErrorException;

public class EffectiveToNominalStrategyImpl implements InterestRateStrategy {
    @Override
    public double convert(InterestRate effectiveRate, int periodInDays) {
        if(effectiveRate instanceof EffectiveRate rate)
        {
            int periodRate = rate.getPeriodType().getValue();
            int capitalization = PeriodType.DAILY.getValue();

            double n = periodInDays / (capitalization * 1.0);
            double m = periodRate / (capitalization * 1.0);

            return (Math.pow(1 + rate.getValue(),1/n) - 1)*m;
        }
        throw new InternalServerErrorException("Invalid rate type");
    }
}
