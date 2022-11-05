package com.quid.sns.comment;

import com.quid.sns.post.Post;
import com.quid.sns.user.User;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Table(name = "comment", indexes = {
    @Index(name = "comment_user_id_idx", columnList = "user_id"),
    @Index(name = "comment_post_id_idx", columnList = "post_id")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @CreationTimestamp
    private LocalDateTime registeredAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime removedAt;

    @Builder
    public Comment(User user, String content, Post post) {
        this.user = user;
        this.content = content;
        this.post = post;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
