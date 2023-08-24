package com.findmypet.services;

import com.findmypet.dtos.LikeDto;

import java.util.List;

public interface ILikeService {

    LikeDto addLike(LikeDto like);

    List<LikeDto> getLikesByPostId(Long postId);

    List<LikeDto> getLikesByUserId(Long userId);

    boolean userHasLikedPost(Long userId, Long postId);

    void deleteLike(Long id);
}
