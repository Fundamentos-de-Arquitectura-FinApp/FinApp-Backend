package com.example.finappapirest.finances.application.internal.strategyservices;

import com.example.finappapirest.finances.domain.model.entities.rate.EffectiveRate;
import com.example.finappapirest.finances.domain.model.entities.rate.InterestRate;
import com.example.finappapirest.finances.domain.model.entities.rate.NominalRate;
import com.example.finappapirest.finances.domain.services.strategies.InterestRateStrategy;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
public class InterestRateContext {
    private InterestRateStrategy strategy;

    public double convertRate(InterestRate rate, int periodInDays) {

        if(rate instanceof NominalRate)
            setStrategy(new NominalToEffectiveStrategyImpl());
        else if(rate instanceof EffectiveRate)
            setStrategy(new EffectiveToEffectiveStrategyImpl());

        return strategy.convert(rate, periodInDays);
    }
}
