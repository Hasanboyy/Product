package com.product.application.controller.hasanboy;

import com.product.application.dto.hasanboy.AuthDto;
import com.product.application.dto.hasanboy.RegisterDto;
import com.product.application.dto.hasanboy.UserDto;
import com.product.application.service.hasanboy.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto dto){
        String result = authService.register(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDto dto){
        AuthDto result = authService.login(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/verification/{token}")
    public ResponseEntity<?> verification(@PathVariable("token") String token){
        UserDto result = authService.verification(token);
        return ResponseEntity.ok(result);
    }
}
