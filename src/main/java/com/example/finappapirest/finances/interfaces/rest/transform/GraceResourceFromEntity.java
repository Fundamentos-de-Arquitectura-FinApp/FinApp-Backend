package com.example.finappapirest.finances.interfaces.rest.transform;

import com.example.finappapirest.finances.domain.model.entities.grace.Grace;
import com.example.finappapirest.finances.interfaces.rest.resources.response.credit.CreateGraceResponse;

public class GraceResourceFromEntity {
    public static CreateGraceResponse fromEntity(Grace grace) {
        return new CreateGraceResponse(grace.getNumQuotas(), grace.getGraceType());
    }
}
