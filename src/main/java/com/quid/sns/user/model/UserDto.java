package com.quid.sns.user.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quid.sns.common.BaseEntity;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto extends BaseEntity implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private UserRole role;

    @Builder
    public UserDto(Long id, String username, String password, UserRole role,
        LocalDateTime registeredAt,
        LocalDateTime updatedAt, LocalDateTime removedAt) {
        super(registeredAt, updatedAt, removedAt);
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return super.getRemovedAt() == null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return super.getRemovedAt() == null;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return super.getRemovedAt() == null;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return super.getRemovedAt() == null;
    }
}
