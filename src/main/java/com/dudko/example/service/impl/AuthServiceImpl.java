package com.dudko.example.service.impl;

import com.dudko.example.domain.entity.Role;
import com.dudko.example.domain.entity.User;
import com.dudko.example.domain.mapper.UserMapper;
import com.dudko.example.domain.repository.RoleRepository;
import com.dudko.example.domain.repository.UserRepository;
import com.dudko.example.model.dto.LoginDto;
import com.dudko.example.model.dto.ProfileDto;
import com.dudko.example.model.dto.RegisterDto;
import com.dudko.example.model.exception.ApplicationApiException;
import com.dudko.example.security.JwtAuthenticationFilter;
import com.dudko.example.security.JwtTokenProvider;
import com.dudko.example.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Override
    public String register(RegisterDto registerDto) {
        User user = User.builder()
                .name(registerDto.getName())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();
        Set<Role> roles = new HashSet<>();
        if (roleRepository.findByName("ROLE_USER") == null) {
            roleRepository.save(Role.builder().name("ROLE_USER").build());
        }
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
        return getToken(registerDto.getEmail(), registerDto.getPassword());
    }

    @Override
    public String login(LoginDto loginDto) {
        return getToken(loginDto.getEmail(), loginDto.getPassword());
    }

    @Override
    public ProfileDto getProfile(HttpServletRequest request) {
        String token = jwtAuthenticationFilter.getTokenFromRequest(request);
        if (jwtTokenProvider.validateToken(token)) {
            String email = jwtTokenProvider.getUsernameFromToken(token);
            User user = userRepository.findByEmail(email).orElseThrow(() -> new ApplicationApiException("Profile not found"));
            return UserMapper.mapToProfileDto(user);
        }
        return null;
    }


    private String getToken(String login, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

}
