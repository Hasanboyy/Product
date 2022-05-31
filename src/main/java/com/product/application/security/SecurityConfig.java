package com.product.application.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("User").password("{noop}user").roles("user").and()
                .withUser("Image").password("{noop}image").roles("image").and()
                .withUser("Address").password("{noop}address77").roles("address").and()
                .withUser("User Role").password("{noop}user_role77").roles("user_role");/*.and()
                .withUser("Order").password("{noop}order77").roles("order").and()
                .withUser("Order Item").password("{noop}order_item77").roles("order_item").and()
                .withUser("Product").password("{noop}product77").roles("product").and()
                .withUser("Product Type").password("{noop}product_type77").roles("product_type").and()
                .withUser("Merchant").password("{noop}merchant77").roles("merchant").and()
                .withUser("Divigatel").password("{noop}divigatel77").roles("divigatel").and()
                .withUser("Brant").password("{noop}brant77").roles("brant");*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").hasRole("user")
                .antMatchers("/image/**").hasRole("image")
                .antMatchers("/address/**").hasRole("address")
                .antMatchers("/user/role/**").hasRole("user_role")
                .antMatchers(HttpMethod.POST, "/image/create").hasRole("user")
                .antMatchers(HttpMethod.POST, "/address/create").hasRole("user")
                .antMatchers(HttpMethod.POST, "/user/role/create").hasRole("user")
                .anyRequest().permitAll()
                .and().httpBasic();


        http.csrf().disable();
    }
}
