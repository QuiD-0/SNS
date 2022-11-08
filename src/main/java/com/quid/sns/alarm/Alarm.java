package com.quid.sns.alarm;

import com.quid.sns.alarm.model.AlarmArgs;
import com.quid.sns.alarm.model.AlarmDto;
import com.quid.sns.alarm.model.AlarmType;
import com.quid.sns.user.User;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Table(name = "alarm", indexes = {
    @Index(name = "idx_alarm_user_id", columnList = "user_id")
})
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private AlarmType type;

    @Embedded
    @Type(type = "jsonb")
    @Column(columnDefinition = "json")
    private AlarmArgs args;

    @CreationTimestamp
    private LocalDateTime registeredAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime removedAt;

    @Builder
    public Alarm(User user, AlarmType type, AlarmArgs args) {
        this.user = user;
        this.type = type;
        this.args = args;
    }

    public AlarmDto toDto() {
        return AlarmDto.builder()
            .id(id)
            .userName(user.getUserName())
            .type(type)
            .args(args)
            .registeredAt(registeredAt)
            .updatedAt(updatedAt)
            .build();
    }
}
