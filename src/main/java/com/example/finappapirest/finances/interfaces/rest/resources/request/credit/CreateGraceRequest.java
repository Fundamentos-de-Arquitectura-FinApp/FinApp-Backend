package com.example.finappapirest.finances.interfaces.rest.resources.request.credit;

import com.example.finappapirest.finances.domain.model.valueobjects.GraceType;

public record CreateGraceRequest(
        Integer numQuotas,
        GraceType type
) {
}
