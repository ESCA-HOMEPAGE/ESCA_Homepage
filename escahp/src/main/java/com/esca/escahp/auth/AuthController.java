package com.esca.escahp.auth;

import com.esca.escahp.auth.dto.LoginRequest;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Auth"})
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class AuthController {
    private final I_AuthService authService;

    @PostMapping()
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request.getUserId(), request.getPassword()));
    }
}
