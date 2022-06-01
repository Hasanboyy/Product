package com.product.application.config;

import com.product.application.model.hasanboy.ProfileEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private final Integer id;
    private final String userName;
    private final String password;
    private final Boolean enabled;
    private final String role;

    private final List<GrantedAuthority> authorityList;

    public CustomUserDetails(ProfileEntity profile){
        this.id = profile.getId();
        this.userName = profile.getUsername();
        this.password = profile.getPassword();
        this.enabled = profile.getEnabled();
        this.role = profile.getRole();
        this.authorityList = Arrays.asList(new SimpleGrantedAuthority(role));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
