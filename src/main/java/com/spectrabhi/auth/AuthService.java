package com.spectrabhi.auth;

import com.spectrabhi.dto.AuthRequest;
import com.spectrabhi.dto.AuthResponse;
import com.spectrabhi.model.User;
import com.spectrabhi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;

/**
 * Service for handling authentication logic such as user registration and
 * login.
 */

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    // public void register(User user) {
    // user.setPassword(passwordEncoder.encode(user.getPassword()));
    // userRepository.save(user);
    // }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // TEMP: Make this user admin based on email
        if (user.getEmail().equals("admin@gmail.com")) {
            user.setRole("ADMIN");
        } else {
            user.setRole("USER");
        }

        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) {
        Authentication auth = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authManager.authenticate(auth); // Will throw error if invalid

        String token = jwtUtil.generateToken(request.getEmail());
        return new AuthResponse(token);
    }
}
