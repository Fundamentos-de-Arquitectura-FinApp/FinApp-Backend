package com.example.finappapirest.finances.domain.model.entities.rate;

import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "type_rate",
        discriminatorType = DiscriminatorType.STRING
)
@AllArgsConstructor
public abstract class InterestRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected Float value;

    @Enumerated(EnumType.STRING)
    protected PeriodType periodType;

    public InterestRate() {

    }
    public InterestRate(Float value, PeriodType periodType) {
        this.value = value;
        this.periodType = periodType;
    }
}
