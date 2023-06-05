package com.jarad.postly.repository;

import com.jarad.postly.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findPostPageByProfileId(Long profileId, Pageable pageable);

    Page<Post> findByProfileUserIdNot(Long userId, Pageable pageable);

    Optional<Post> findByProfileIdAndId(Long profileId, Long postId);

    Optional<Post> findByProfileUserIdAndId(Long userId, Long postId);

    boolean existsByProfileUserIdAndId(Long userId, Long postId);


}