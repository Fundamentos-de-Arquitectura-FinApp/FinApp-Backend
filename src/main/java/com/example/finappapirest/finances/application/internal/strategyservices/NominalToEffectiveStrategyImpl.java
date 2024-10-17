package com.example.finappapirest.finances.application.internal.strategyservices;

import com.example.finappapirest.finances.domain.model.entities.rate.InterestRate;
import com.example.finappapirest.finances.domain.model.entities.rate.NominalRate;
import com.example.finappapirest.finances.domain.services.strategies.InterestRateStrategy;
import com.example.finappapirest.shared.domain.model.exceptions.InternalServerErrorException;

public class NominalToEffectiveStrategyImpl implements InterestRateStrategy {

    //Formula: i = (1 + TNP/m)^n - 1
    @Override
    public double convert(InterestRate nominalRate, int periodInDays) {
        if (nominalRate instanceof NominalRate rate) {

            int periodRate = rate.getPeriodType().getValue();
            int capitalization = rate.getCapitalizationPeriod().getValue();
            double n = periodInDays / (capitalization * 1.0);
            double m = periodRate / (capitalization * 1.0);

            return Math.pow(1 + rate.getValue() / m, n) - 1;
        }
        throw new InternalServerErrorException("Invalid rate type");
    }
}
