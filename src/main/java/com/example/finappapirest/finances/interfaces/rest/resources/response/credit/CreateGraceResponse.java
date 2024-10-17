package com.example.finappapirest.finances.interfaces.rest.resources.response.credit;

import com.example.finappapirest.finances.domain.model.valueobjects.GraceType;

public record CreateGraceResponse(
        Integer numQuotas,
        GraceType type
) {
}
