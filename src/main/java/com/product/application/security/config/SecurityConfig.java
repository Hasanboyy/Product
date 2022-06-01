package com.product.application.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailService customUserDetailService;

    private JwtTokenFilter jwtTokenFilter;

    private AuthEntityPointJwt jwtAuthEntityPoint;


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(customUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().cors().disable();

        http.authorizeRequests()
                .antMatchers("/user/**").hasRole("user")
                .antMatchers("/image/**").hasRole("image")
                .antMatchers("/address/**").hasRole("address")
                .antMatchers("/user/role/**").hasRole("user_role")
                .antMatchers(HttpMethod.POST, "/image/create").hasRole("user")
                .antMatchers(HttpMethod.POST, "/address/create").hasRole("user")
                .antMatchers(HttpMethod.POST, "/user/role/create").hasRole("user")
                .anyRequest().permitAll().and()
                .formLogin().permitAll();

        http.addFilterBefore(
                jwtTokenFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        http.csrf().disable();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
