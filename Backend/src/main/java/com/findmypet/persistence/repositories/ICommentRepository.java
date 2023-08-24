package com.findmypet.persistence.repositories;

import com.findmypet.persistence.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment,Long> {
    Page<Comment> findAllByPostId(Long idPost, Pageable pageable);
}
