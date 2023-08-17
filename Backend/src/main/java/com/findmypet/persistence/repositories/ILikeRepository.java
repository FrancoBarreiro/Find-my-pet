package com.findmypet.persistence.repositories;

import com.findmypet.persistence.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILikeRepository extends JpaRepository<Like,Long> {
}
