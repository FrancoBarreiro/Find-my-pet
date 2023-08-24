package com.findmypet.persistence.repositories;

import com.findmypet.persistence.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILikeRepository extends JpaRepository<Like,Long> {

    List<Like> findAllByPostId(Long postId);

    List<Like> findAllByUserId(Long userId);

    boolean existsByUserIdAndPostId(Long userId, Long postId);

}
