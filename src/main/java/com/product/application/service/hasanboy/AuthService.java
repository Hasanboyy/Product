package com.product.application.service.hasanboy;

import com.product.application.config.JwtTokenUtil;
import com.product.application.dto.hasanboy.AuthDto;
import com.product.application.dto.hasanboy.RegisterDto;
import com.product.application.dto.hasanboy.UserDto;
import com.product.application.repository.hasanboy.UserRepository;
import com.product.application.repository.hasanboy.UserTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private MessageService messageService;
    private JwtTokenUtil jwtTokenUtil;
    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;
    private PasswordEncoder passwordEncoder;

    public String register(RegisterDto dto) {
        return null;
    }

    public AuthDto login(AuthDto dto) {
        return null;
    }

    public UserDto verification(String token) {
        return null;
    }
}
