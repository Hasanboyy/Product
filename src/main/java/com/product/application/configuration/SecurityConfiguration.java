package com.product.application.configuration;


import com.product.application.dto.hasanboy.ROLES;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@EnableWebSecurity
@AllArgsConstructor
//Butun sekurity uchun javob beradigan class
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private CustomUserDetailsService customUserDetailsService; // Managment users
    private JwtTokenFilter jwtTokenFilter; //Managment token
    private AuthEntryPointJwt jwtAuthEntityPoint; // Managment token


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http.authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/user/admin/**").hasRole(String.valueOf(ROLES.ADMIN))
                .antMatchers("/user-type/**").authenticated()
                .antMatchers("/product/**").permitAll()
                .anyRequest().permitAll();

        //http.exceptionHandling().authenticationEntryPoint(jwtAuthEntityPoint);

        http.addFilterBefore(
                jwtTokenFilter,
                UsernamePasswordAuthenticationFilter.class
        );
    }

    // Details omitted for brevity
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
