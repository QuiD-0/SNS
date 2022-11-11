package com.quid.sns.user.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private UserRole role;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime registeredAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime removedAt;

    @Builder
    public UserDto(Long id, String username, String password, UserRole role,
        LocalDateTime registeredAt,
        LocalDateTime updatedAt, LocalDateTime removedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.registeredAt = registeredAt;
        this.updatedAt = updatedAt;
        this.removedAt = removedAt;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return removedAt == null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return removedAt == null;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return removedAt == null;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return removedAt == null;
    }
}
