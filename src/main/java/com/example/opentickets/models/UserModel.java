package com.example.opentickets.models;

import com.example.opentickets.entities.UserEntity;
import com.example.opentickets.models.enums.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserModel implements UserDetails {

    private String email;

    private String password;

    private UserRole userRole;

    private Set<SimpleGrantedAuthority> authoritySet;

    public static UserModel fromEntity(UserEntity userEntity){
        return new UserModel(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getUserRole(),
                userEntity.getUserRole().getAuthorities()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authoritySet;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }
}
