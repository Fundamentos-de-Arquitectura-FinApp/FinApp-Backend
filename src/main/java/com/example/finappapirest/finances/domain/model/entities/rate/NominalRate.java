package com.example.finappapirest.finances.domain.model.entities.rate;

import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("nominal")
public class NominalRate extends InterestRate{
    private PeriodType capitalizationPeriod;

    public NominalRate() {
        super();
    }
    public NominalRate(Float rate, PeriodType periodType, PeriodType capitalizationPeriod) {
        super(rate,periodType);
        this.capitalizationPeriod = capitalizationPeriod;
    }
}
