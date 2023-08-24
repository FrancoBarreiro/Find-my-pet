package com.findmypet.services.impl;

import com.findmypet.dtos.PostDto;
import com.findmypet.exceptions.BadRequestException;
import com.findmypet.exceptions.ResourceNotFoundException;
import com.findmypet.mappers.PostMapper;
import com.findmypet.persistence.entities.Post;
import com.findmypet.persistence.repositories.IPostRepository;
import com.findmypet.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import jakarta.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {

    private final Validator validator;

    @Autowired
    private IPostRepository postRepository;

    public PostService(Validator validator) {
        this.validator = validator;
    }

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

    @Override
    public PostDto getPostById(Long id) {
        Optional<Post> postFound = postRepository.findById(id);

        if (postFound.isPresent()) {
            return PostMapper.entityToDto(postFound.get());
        } else {
            throw new ResourceNotFoundException("The post with id :" + id + " was not found");
        }
    }

    @Override
    public Page<PostDto> getPostsPaged(int size, int page) {
        Sort sort = Sort.by("date").ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Post> postsFound = postRepository.findAll(pageable);
        List<PostDto> posts = new ArrayList<>();

        for (Post p : postsFound) {
            PostDto post = PostMapper.entityToDto(p);
            posts.add(post);
        }
        return new PageImpl<>(posts, postsFound.getPageable(), postsFound.getTotalElements());
    }

    @Override
    public PostDto updatePost(PostDto post) {
        Optional<Post> postFound = postRepository.findById(post.getId());

        if (postFound.isPresent() && validator.validate(post).isEmpty()) {
            Post postToUpdate = PostMapper.updateEntityFromDto(post);
            postRepository.save(postToUpdate);
            return post;
        } else {
            throw new ResourceNotFoundException("Post not found");
        }
    }

    @Override
    public void deletePost(Long id) {
        Optional<Post> postFound = postRepository.findById(id);

        if (postFound.isPresent()) {
            postRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Post not found");
        }
    }
}
