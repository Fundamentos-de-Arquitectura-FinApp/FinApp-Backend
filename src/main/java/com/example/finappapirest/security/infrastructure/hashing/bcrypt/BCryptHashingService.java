package com.example.finappapirest.security.infrastructure.hashing.bcrypt;

import com.example.finappapirest.security.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
