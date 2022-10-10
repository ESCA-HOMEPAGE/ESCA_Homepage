package com.esca.escahp.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {
    String userId;
    String password;
    String jwtToken;
}
