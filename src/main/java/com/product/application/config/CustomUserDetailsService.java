package com.product.application.config;

import com.product.application.model.hasanboy.ProfileEntity;
import com.product.application.repository.hasanboy.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Keldi: loadUserByUsername.");
        Optional<ProfileEntity> usersOptional = this.profileRepository.findByUserName(username);
        usersOptional.orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        ProfileEntity profile = usersOptional.get();
        System.out.println(profile);

        return new CustomUserDetails(profile);
    }
}
