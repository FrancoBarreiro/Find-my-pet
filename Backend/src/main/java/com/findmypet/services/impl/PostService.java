package com.findmypet.services.impl;

import com.findmypet.dtos.PostDto;
import com.findmypet.persistence.entities.Post;
import com.findmypet.persistence.repositories.IPostRepository;
import com.findmypet.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {

    @Autowired
    private IPostRepository postRepository;

    @Override
    public Post addPost(Post post) {
        return postRepository.save(post);
    }
}
