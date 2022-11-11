package com.quid.sns.post;

import com.quid.sns.common.BaseEntity;
import com.quid.sns.post.model.PostDto;
import com.quid.sns.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Entity
@Table(name = "post", indexes = {
    @Index(name = "post_user_id_idx", columnList = "user_id")
})
@SQLDelete(sql = "UPDATE \"post\" SET removed_at = NOW() WHERE id=?")
@Where(clause = "removed_at is NULL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String title;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public void updatePost(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public PostDto toDto() {
        return PostDto.builder()
            .id(id)
            .title(title)
            .body(body)
            .userName(user.getUserName())
            .registeredAt(this.getRegisteredAt())
            .updatedAt(this.getUpdatedAt())
            .build();
    }
}
