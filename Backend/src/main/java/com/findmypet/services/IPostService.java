package com.findmypet.services;

import com.findmypet.dtos.PostDto;
import com.findmypet.persistence.entities.Post;

public interface IPostService {

    Post addPost(Post post);

}
