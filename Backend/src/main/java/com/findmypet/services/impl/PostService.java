package com.findmypet.services.impl;

import com.findmypet.dtos.PostDto;
import com.findmypet.exceptions.BadRequestException;
import com.findmypet.mappers.PostMapper;
import com.findmypet.persistence.entities.Post;
import com.findmypet.persistence.repositories.IPostRepository;
import com.findmypet.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.validation.Validator;

@Service
public class PostService implements IPostService {

    private final Validator validator;

    @Autowired
    private IPostRepository postRepository;

    public PostService(Validator validator) {this.validator = validator;}

    @Override
    public PostDto addPost(PostDto post) {

        if (validator.validate(post).isEmpty()) {
            Post postToSave = PostMapper.dtoToEntity(post);
            postRepository.save(postToSave);
            post.setId(postToSave.getId());
            return post;
        } else {
            throw new BadRequestException("Check the fields");
        }
    }
}
