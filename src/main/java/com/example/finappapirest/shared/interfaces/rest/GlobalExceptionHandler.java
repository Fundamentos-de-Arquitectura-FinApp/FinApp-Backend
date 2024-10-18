package com.example.finappapirest.shared.interfaces.rest;

import com.example.finappapirest.shared.domain.model.entities.ErrorResponse;
import com.example.finappapirest.shared.domain.model.exceptions.BadRequestException;
import com.example.finappapirest.shared.domain.model.exceptions.InternalServerErrorException;
import com.example.finappapirest.shared.domain.model.exceptions.NotFoundException;
import com.example.finappapirest.shared.domain.model.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse("Not found" ,e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handle(InternalServerErrorException e) {
        ErrorResponse errorResponse = new ErrorResponse("Internal server error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handle(BadRequestException e) {
        ErrorResponse errorResponse = new ErrorResponse("Bad request", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handle(UnauthorizedException e) {
        ErrorResponse errorResponse = new ErrorResponse("Unauthorized", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}
