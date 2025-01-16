package com.dudko.example.service;

import com.dudko.example.model.dto.LoginDto;
import com.dudko.example.model.dto.ProfileDto;
import com.dudko.example.model.dto.RegisterDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);

    ProfileDto getProfile(HttpServletRequest request);

}
