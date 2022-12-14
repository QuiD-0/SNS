package com.quid.sns.user;

import com.quid.sns.common.BaseEntity;
import com.quid.sns.user.model.UserDto;
import com.quid.sns.user.model.UserRole;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@ToString
@SQLDelete(sql = "UPDATE \"user\" SET removed_at = NOW() WHERE id=?")
@Where(clause = "removed_at is NULL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", unique = true)
    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Builder
    public User(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    public UserDto toUserDto() {
        return UserDto.builder()
            .id(id)
            .username(userName)
            .role(role)
            .registeredAt(this.getRegisteredAt())
            .updatedAt(this.getUpdatedAt())
            .removedAt(this.getRemovedAt())
            .build();
    }
}
