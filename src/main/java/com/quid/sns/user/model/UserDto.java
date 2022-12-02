package com.quid.sns.user.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDto(
    Long id,
    String username,
    String password,
    UserRole role,
    LocalDateTime registeredAt,
    LocalDateTime updatedAt,
    LocalDateTime removedAt
) implements UserDetails {

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    public boolean isAccountNonExpired() {
        return removedAt == null;
    }

    @JsonIgnore
    public boolean isAccountNonLocked() {
        return removedAt == null;
    }

    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return removedAt == null;
    }

    @JsonIgnore
    public boolean isEnabled() {
        return removedAt == null;
    }
}
