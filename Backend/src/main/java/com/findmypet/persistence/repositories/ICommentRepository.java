package com.findmypet.persistence.repositories;

import com.findmypet.persistence.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment,Long> {
}
