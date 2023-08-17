package com.findmypet.services.impl;

import com.findmypet.persistence.entities.Like;
import com.findmypet.persistence.repositories.ILikeRepository;
import com.findmypet.services.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService implements ILikeService {

    @Autowired
    private ILikeRepository likeRepository;

    @Override
    public Like addLike(Like like) {
        return likeRepository.save(like);
    }
}
