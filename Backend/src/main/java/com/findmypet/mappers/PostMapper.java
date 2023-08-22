package com.findmypet.mappers;

import com.findmypet.dtos.PostDto;
import com.findmypet.exceptions.BadRequestException;
import com.findmypet.persistence.entities.Post;
import com.findmypet.persistence.entities.User;
import com.findmypet.utils.NotificationType;
import com.findmypet.utils.PostType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class PostMapper {

    public static PostDto entityToDto(Post post) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return PostDto.builder()
                .id(post.getId())
                .description(post.getDescription())
                .type(PostType.valueOf(post.getType()))
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .date(post.getDate().format(dateFormatter))
                .hour(post.getDate().format(timeFormatter))
                .userId(post.getUser().getId())
                .imageUrls(new ArrayList<>(post.getImageUrls()))
                .comments(post.getComments() != null ? post.getComments().stream()
                        .map(comment -> CommentMapper.entityToDto(comment))
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .likes(post.getLikes() != null ? post.getLikes().stream()
                        .map(like -> LikeMapper.entityToDto(like))
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }

    public static Post dtoToEntity(PostDto post) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime date = LocalDateTime.parse(
                post.getDate() + " " + post.getHour(),
                localDateTimeFormatter);

        return Post.builder()
                .id(post.getId())
                .description(post.getDescription())
                .type(post.getType().toString())
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .date(date)
                .user(User.builder().id(post.getUserId()).build())
                .imageUrls(new ArrayList<>(post.getImageUrls()))
                .comments(post.getComments() != null ? post.getComments().stream()
                        .map(comment -> CommentMapper.dtoToEntity(comment))
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .likes(post.getLikes() != null ? post.getLikes().stream()
                        .map(like -> LikeMapper.dtoToEntity(like))
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }


}
