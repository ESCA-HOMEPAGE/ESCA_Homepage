package com.esca.escahp.auth;

import com.esca.escahp.auth.dto.LoginRequest;

public interface I_AuthService {
    String login(LoginRequest loginRequest);
}
