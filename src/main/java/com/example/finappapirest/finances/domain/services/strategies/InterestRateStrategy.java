package com.example.finappapirest.finances.domain.services.strategies;

import com.example.finappapirest.finances.domain.model.entities.rate.InterestRate;

public interface InterestRateStrategy {
    double convert(InterestRate rate, int periodInDays);
}
