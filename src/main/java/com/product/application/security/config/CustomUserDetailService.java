package com.product.application.security.config;

import com.product.application.security.model.ProfileEntity;
import com.product.application.security.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.hibernate.event.spi.PreDeleteEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<ProfileEntity> userOptional = this.profileRepository.findByUserName(username);
        userOptional.orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        ProfileEntity profile = userOptional.get();
        System.out.println(profile);

        return new CustomUserDetails(profile);
    }
}
