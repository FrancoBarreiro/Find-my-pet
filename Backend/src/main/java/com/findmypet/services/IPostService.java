package com.findmypet.services;

import com.findmypet.dtos.PostDto;
import org.springframework.data.domain.Page;

public interface IPostService {

    PostDto addPost(PostDto post);

    PostDto getPostById(Long id);

    Page<PostDto> getPostsPaged(int size, int page);

    PostDto updatePost(PostDto post);

    void deletePost(Long id);

}
