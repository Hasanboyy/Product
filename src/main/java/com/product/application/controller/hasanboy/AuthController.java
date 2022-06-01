package com.product.application.controller.hasanboy;

import com.product.application.config.JwtTokenUtil;
import com.product.application.dto.hasanboy.AuthorizationDto;
import com.product.application.dto.hasanboy.ProfileDetailDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<ProfileDetailDto> login(@RequestBody AuthorizationDto request){
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            request.getUserName(),
                            request.getPassword()));

            UserDetails user = (UserDetails) authentication.getPrincipal();

            String jwtToken = jwtTokenUtil.generateAccessToken(user);

            ProfileDetailDto dto = new ProfileDetailDto();
            dto.setUserName(user.getUsername());
            dto.setToken(jwtToken);

            return ResponseEntity.ok().
                    header(HttpHeaders.AUTHORIZATION, jwtToken)
                    .body(dto);
        }catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
