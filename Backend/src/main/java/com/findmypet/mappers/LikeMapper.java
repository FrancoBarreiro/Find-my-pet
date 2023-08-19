package com.findmypet.mappers;

import com.findmypet.dtos.LikeDto;
import com.findmypet.persistence.entities.Like;
import com.findmypet.persistence.entities.Post;
import com.findmypet.persistence.entities.User;

public class LikeMapper {
    public static LikeDto entityToDto(Like like) {
        return LikeDto.builder()
                .id(like.getId())
                .userId(like.getUser().getId())
                .postId(like.getPost().getId())
                .build();

    }

    public static Like dtoToEntity(LikeDto like) {
        return Like.builder()
                .id(like.getId())
                .user(User.builder().id(like.getUserId()).build())
                .post(Post.builder().id(like.getPostId()).build())
                .build();
    }
}

