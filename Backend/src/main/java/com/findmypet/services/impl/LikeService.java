package com.findmypet.services.impl;

import com.findmypet.dtos.LikeDto;
import com.findmypet.exceptions.BadRequestException;
import com.findmypet.exceptions.ResourceNotFoundException;
import com.findmypet.mappers.LikeMapper;
import com.findmypet.persistence.entities.Like;
import com.findmypet.persistence.entities.Post;
import com.findmypet.persistence.entities.User;
import com.findmypet.persistence.repositories.ILikeRepository;
import com.findmypet.persistence.repositories.IPostRepository;
import com.findmypet.persistence.repositories.IUserRepository;
import com.findmypet.services.ILikeService;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService implements ILikeService {

    private final Validator validator;

    @Autowired
    private ILikeRepository likeRepository;

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IUserRepository userRepository;

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

    @Override
    public List<LikeDto> getLikesByPostId(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new ResourceNotFoundException("The post with id " + postId + " was not found.");
        }

        List<Like> likes = likeRepository.findAllByPostId(postId);

        if (likes != null && !likes.isEmpty()) {
            return likes.stream().map(like -> LikeMapper.entityToDto(like)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<LikeDto> getLikesByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("The user with id " + userId + " was not found.");
        }

        List<Like> likes = likeRepository.findAllByUserId(userId);

        if (likes != null && !likes.isEmpty()) {
            return likes.stream().map(like -> LikeMapper.entityToDto(like)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public boolean userHasLikedPost(Long userId, Long postId) {
        return likeRepository.existsByUserIdAndPostId(userId, postId);
    }

    @Override
    public void deleteLike(Long id) {
        Optional<Like> like = likeRepository.findById(id);

        if (like.isPresent()) {
            likeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Like not found");
        }
    }
}
