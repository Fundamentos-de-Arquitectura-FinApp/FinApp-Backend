package com.example.finappapirest.finances.domain.model.entities.grace;

import com.example.finappapirest.finances.domain.model.valueobjects.GraceType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Grace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private GraceType graceType;
    private Integer numQuotas;
}
