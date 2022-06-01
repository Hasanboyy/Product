package com.product.application.security.config;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenUtil jwtTokenUtil;
    private CustomUserDetailService customUserDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        System.out.println("Filterga Keldi");

        final String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")){
            chain.doFilter(request,response);
        }

        final String token = header.split(",")[1].trim();
        if (!jwtTokenUtil.validate(token)){
            chain.doFilter(request,response);
        }
    }
}
