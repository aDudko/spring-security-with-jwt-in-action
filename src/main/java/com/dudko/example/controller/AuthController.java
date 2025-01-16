package com.dudko.example.controller;

import com.dudko.example.model.dto.JwtAuthResponse;
import com.dudko.example.model.dto.LoginDto;
import com.dudko.example.model.dto.ProfileDto;
import com.dudko.example.model.dto.RegisterDto;
import com.dudko.example.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User REST API")
@AllArgsConstructor
@RestController
@RequestMapping(value = "/auth", produces = "application/vnd.api.v1+json")
public class AuthController {

    private final AuthService authService;


    @Operation(
            summary = "Register new user",
            description = "Register new user  and save in the database"
    )
    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponse> register(@Valid @RequestBody RegisterDto registerDto) {
        String token = authService.register(registerDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @Operation(
            summary = "To login as exist user",
            description = "User authorization by email and password"
    )
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @Operation(
            summary = "Get profile data",
            description = "To get profile data for exist user"
    )
    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getProfile(HttpServletRequest request) {
        ProfileDto profile = authService.getProfile(request);
        return ResponseEntity.ok(profile);
    }

}
