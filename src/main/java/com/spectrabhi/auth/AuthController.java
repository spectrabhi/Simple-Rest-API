package com.spectrabhi.auth;

import com.spectrabhi.dto.AuthRequest;
import com.spectrabhi.dto.AuthResponse;
import com.spectrabhi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        authService.register(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }
}
