package com.quid.sns.post.repository;


import com.quid.sns.post.Post;
import com.quid.sns.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository postJpaRepository;

    @Override
    public Page<Post> findByUser(User user, Pageable pageable) {
        return postJpaRepository.findByUser(user, pageable);
    }

    @Override
    public Page<Post> searchPost(String keyword1, String keyword2, Pageable pageable) {
        return postJpaRepository.findByTitleContainingOrBodyContaining(keyword1, keyword2,
            pageable);
    }

    @Override
    public Post findByIdOrThrow(Long postId) {
        return postJpaRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postJpaRepository.findAll(pageable);
    }

    @Override
    public void save(Post post) {
        postJpaRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        postJpaRepository.deleteById(id);
    }


}
