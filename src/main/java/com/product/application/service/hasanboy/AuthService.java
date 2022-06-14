package com.product.application.service.hasanboy;

import com.product.application.configuration.JwtTokenUtil;
import com.product.application.dto.hasanboy.AuthDto;
import com.product.application.dto.hasanboy.RegisterDto;
import com.product.application.dto.hasanboy.UserDto;
import com.product.application.exception.ProductException;
import com.product.application.model.hasanboy.User;
import com.product.application.model.hasanboy.UserType;
import com.product.application.repository.hasanboy.UserRepository;
import com.product.application.repository.hasanboy.UserTypeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private MessageService messageService;
    private JwtTokenUtil jwtTokenUtil;
    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;

    private PasswordEncoder passwordEncoder;//birtomonlama shifirlash

    public String register(RegisterDto dto) {
        Optional<User> optional = userRepository.findByEmailOrContactAndDeletedAtIsNull(dto.getEmail(), dto.getContact());
        if (optional.isPresent()) {
            throw new ProductException("User already exist");
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setContact(dto.getContact());
        user.setPassword(PasswordService.generateMD5(dto.getPassword()));
        user.setStatus(false);
        user.setCreatedAt(LocalDateTime.now());
        //user.setUserTypeId(2);

        /*UserType userType = new UserType();
        userType.setName("ROLE_USER");
        userType.setCreatedAt(LocalDateTime.now());
        userType.setStatus(true);
        userTypeRepository.save(userType);*/
        userRepository.save(user);

        String token = jwtTokenUtil.generateAccessToken(user.getId(), user.getEmail());
        String link = "http://localhost:8080/auth/verification" + token;
        String content = String.format("Please click %s for verification", link);
        try {
            messageService.send(user.getEmail(), "iSystem shop uz verification", content);
        } catch (Exception e) {
            userRepository.delete(user);
            e.printStackTrace();
            return "Mail not delivered";
        }
        return "Please go to " + dto.getEmail() + " and verification your data";
    }

    public UserDto verification(String token) {
        String userName = jwtTokenUtil.getUserName(token);
        Optional<User> optional = userRepository.findByEmailAndDeletedAtIsNull(userName);
        if (optional.isEmpty()) {
            throw new ProductException("User not found");
        }
        User user = optional.get();
        user.setStatus(true);
        userRepository.save(user);
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setName(user.getName());
        return dto;
    }

    public AuthDto login(AuthDto dto) {
        Optional<User> optional = userRepository
                .findByEmailAndPasswordAndDeletedAtIsNull(dto.getEmail(), PasswordService.generateMD5(dto.getPassword()));
        if (optional.isEmpty()) {
            throw new ProductException("User not found");
        }
        User user = optional.get();
        String token = jwtTokenUtil.generateAccessToken(user.getId(), user.getEmail());
        dto.setToken(token);
        return dto;
    }
}