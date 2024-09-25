package com.example.finappapirest.finances.domain.model.entities.rate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("effective")
public class EffectiveRate extends InterestRate{
}
