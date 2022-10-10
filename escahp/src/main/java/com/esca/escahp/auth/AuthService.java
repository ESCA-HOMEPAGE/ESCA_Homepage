package com.esca.escahp.auth;

import com.esca.escahp.auth.config.JwtTokenProvider;
import com.esca.escahp.user.UserService;
import com.esca.escahp.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements I_AuthService {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public String login(String userId, String password) {
        User user = userService.findUserByLoginInfo(userId, password);
        return jwtTokenProvider.createToken(user.getUserId());
    }
}
