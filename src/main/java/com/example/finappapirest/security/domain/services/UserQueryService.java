package com.example.finappapirest.security.domain.services;

import com.example.finappapirest.security.domain.model.aggregates.User;
import com.example.finappapirest.security.domain.model.queries.GetAllUsersQuery;
import com.example.finappapirest.security.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
}
