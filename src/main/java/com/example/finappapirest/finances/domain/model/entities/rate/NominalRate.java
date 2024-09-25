package com.example.finappapirest.finances.domain.model.entities.rate;

import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("nominal")
public class NominalRate extends InterestRate{
    private PeriodType capitalizationPeriod;
}
