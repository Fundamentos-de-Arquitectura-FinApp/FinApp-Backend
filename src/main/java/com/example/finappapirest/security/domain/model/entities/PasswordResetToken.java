package com.example.finappapirest.security.domain.model.entities;

import com.example.finappapirest.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class PasswordResetToken extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Long userId;
    private boolean used;
    private Date expirationDate;

    public boolean isTokenExpired() {
        return expirationDate.before(new Date());
    }
}
