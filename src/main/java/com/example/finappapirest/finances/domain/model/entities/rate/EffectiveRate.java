package com.example.finappapirest.finances.domain.model.entities.rate;

import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("effective")
public class EffectiveRate extends InterestRate{
    public EffectiveRate() {
        super();
    }
    public EffectiveRate(Float value, PeriodType periodType) {
        super(value,periodType);
    }
}
