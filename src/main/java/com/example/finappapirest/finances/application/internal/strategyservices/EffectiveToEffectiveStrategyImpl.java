package com.example.finappapirest.finances.application.internal.strategyservices;

import com.example.finappapirest.finances.domain.model.entities.rate.EffectiveRate;
import com.example.finappapirest.finances.domain.model.entities.rate.InterestRate;
import com.example.finappapirest.finances.domain.services.strategies.InterestRateStrategy;
import com.example.finappapirest.shared.domain.model.exceptions.InternalServerErrorException;

public class EffectiveToEffectiveStrategyImpl implements InterestRateStrategy {

    //Formula: TEP2 = (1 + TEP1)^(n2/n1) - 1
    @Override
    public double convert(InterestRate effectiveRate, int periodInDays) {
        if(effectiveRate instanceof EffectiveRate rate)
        {
            int n2 = periodInDays;
            int n1 = rate.getPeriodType().getValue();
            return Math.pow(1 + rate.getValue(), n2/(n1*1.0)) - 1;
        }
        throw new InternalServerErrorException("Invalid rate type");
    }
}
