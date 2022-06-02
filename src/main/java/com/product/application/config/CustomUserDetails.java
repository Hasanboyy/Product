package com.product.application.config;

import com.product.application.model.hasanboy.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Foydalanuvchini va securityga o`tqizib berishlik uchun
public class CustomUserDetails implements UserDetails {
    private final Integer id;
    private final String userName;
    private final String password;
    private final Boolean enabled;

    private final List<GrantedAuthority> roleList;

    public CustomUserDetails(User user){
        this.id = user.getId();
        this.userName = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.getStatus();
        this.roleList = List.of(new SimpleGrantedAuthority(user.getUserRole().getName()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleList;
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
