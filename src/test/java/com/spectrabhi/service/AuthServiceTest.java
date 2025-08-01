package com.spectrabhi.service;

import com.spectrabhi.auth.AuthService;
import com.spectrabhi.auth.JwtUtil;
import com.spectrabhi.dto.AuthRequest;
import com.spectrabhi.dto.AuthResponse;
import com.spectrabhi.model.User;
import com.spectrabhi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginReturnsToken() {
        AuthRequest req = new AuthRequest("test.dev@gmail.com.com", "test123");

        when(jwtUtil.generateToken(req.getEmail())).thenReturn("mock-token");
        when(authManager.authenticate(any())).thenReturn(mock(Authentication.class));

        AuthResponse res = authService.login(req);

        assertEquals("mock-token", res.getToken());
    }

    @Test
    void testRegisterAssignsAdminRole() {
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword("raw-password");

        when(passwordEncoder.encode("raw-password")).thenReturn("encoded-password");

        authService.register(user);

        assertEquals("encoded-password", user.getPassword());
        assertEquals("ADMIN", user.getRole());
        verify(userRepo).save(user);
    }

    @Test
    void testRegisterAssignsUserRole() {
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("123456");

        when(passwordEncoder.encode("123456")).thenReturn("encoded-123456");

        authService.register(user);

        assertEquals("encoded-123456", user.getPassword());
        assertEquals("USER", user.getRole());
        verify(userRepo).save(user);
    }

    @Test
    void testRegisterAdminUser() {
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword("plainpass");

        when(passwordEncoder.encode("plainpass")).thenReturn("encodedpass");
        when(userRepo.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        authService.register(user);

        assertEquals("encodedpass", user.getPassword());
        assertEquals("ADMIN", user.getRole());
        verify(userRepo).save(user);
    }

    @Test
    void testRegisterNormalUser() {
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("plainpass");

        when(passwordEncoder.encode("plainpass")).thenReturn("encodedpass");
        when(userRepo.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        authService.register(user);

        assertEquals("encodedpass", user.getPassword());
        assertEquals("USER", user.getRole());
        verify(userRepo).save(user);
    }

}
