package com.example.finappapirest.finances.domain.model.entities.rate;

import com.example.finappapirest.finances.domain.model.valueobjects.PeriodType;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "type_rate",
        discriminatorType = DiscriminatorType.STRING
)
public abstract class InterestRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float amount;
    private Double value;

    @Enumerated(EnumType.STRING)
    private PeriodType periodType;
}
