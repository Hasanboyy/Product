package com.product.application.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("user")
                .and()
                .withUser("image").password("{noop}image").roles("image")
                .and()
                .withUser("address").password("{noop}address77").roles("address")
                .and()
                .withUser("User Role").password("user_role77").roles("user_role");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user").hasRole("user")
                .antMatchers("/image").hasRole("image")
                .antMatchers("/adress").hasRole("address")
                .antMatchers("/user/role").hasRole("user_role")
                .antMatchers("").hasRole("")
                .anyRequest().permitAll()
                .and().httpBasic();
    }
}
