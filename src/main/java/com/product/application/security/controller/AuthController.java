package com.product.application.security.controller;

import com.product.application.security.dto.AuthorizationDTO;
import com.product.application.security.dto.ProfileDetailDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    private AuthenticationManager authenticationManager;

    //private JwtTokenUtil jwtTokenUtil;



    @PostMapping("/login")
    public ResponseEntity<ProfileDetailDto> login(@RequestBody AuthorizationDTO requset){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(requset.getUserName(),requset.getPassword()));
        UserDetails user = (UserDetails) authentication.getPrincipal();


        return null;
    }
}
