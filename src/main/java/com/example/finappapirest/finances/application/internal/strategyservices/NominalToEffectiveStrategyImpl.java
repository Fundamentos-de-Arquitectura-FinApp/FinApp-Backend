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

            System.out.println("Type rate: Nominal");
            System.out.println("n: " + n);
            System.out.println("m: " + m);
            System.out.println("rate: " + rate.getValue());
            System.out.println("periodInDays: " + periodInDays);
            double result = Math.pow(1 + rate.getValue() / m, n) - 1;
            System.out.println("result: " + result);

            return result;
        }
        throw new InternalServerErrorException("Invalid rate type");
    }
}
