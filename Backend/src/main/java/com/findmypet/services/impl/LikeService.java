package com.findmypet.services.impl;

import com.findmypet.dtos.LikeDto;
import com.findmypet.exceptions.BadRequestException;
import com.findmypet.mappers.LikeMapper;
import com.findmypet.persistence.entities.Like;
import com.findmypet.persistence.repositories.ILikeRepository;
import com.findmypet.services.ILikeService;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService implements ILikeService {

    private final Validator validator;

    @Autowired
    private ILikeRepository likeRepository;

    public LikeService(Validator validator) {
        this.validator = validator;
    }

    @Override
    public LikeDto addLike(LikeDto like) {

        if (validator.validate(like).isEmpty()) {
            Like likeToSave = LikeMapper.dtoToEntity(like);
            likeRepository.save(likeToSave);
            like.setId(likeToSave.getId());
            return like;
        } else {
            throw new BadRequestException("Check the fields");
        }
    }
}
