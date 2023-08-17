package com.findmypet.persistence.repositories;

import com.findmypet.persistence.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<Post,Long> {
}
