package com.findmypet.mappers;

import com.findmypet.dtos.PostDto;
import com.findmypet.persistence.entities.Comment;
import com.findmypet.persistence.entities.Like;
import com.findmypet.persistence.entities.Post;
import com.findmypet.persistence.entities.User;
import com.findmypet.utils.PostType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PostMapper {


    public static PostDto entityToDto(Post post) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return PostDto.builder()
                .id(post.getId())
                .type(PostType.valueOf(post.getType()))
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .date(post.getDate().format(dateFormatter))
                .hour(post.getDate().format(timeFormatter))
                .userId(post.getUser().getId())
                .imageUrls(new ArrayList<>(post.getImageUrls()))
                .comments(post.getComments().stream()
                        .map(comment -> CommentMapper.entityToDto(comment))
                        .collect(Collectors.toList()))
                .build();
    }

    public static Post dtoToEntity(PostDto post) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime date = LocalDateTime.parse(
                post.getDate() + " " + post.getHour(),
                localDateTimeFormatter
        );

        return Post.builder()
                .id(post.getId())
                .type(post.getType().toString())
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .date(date)
                .user(User.builder().id(post.getUserId()).build())
                .imageUrls(new ArrayList<>(post.getImageUrls()))
                .comments(post.getComments().stream()
                        .map(comment -> CommentMapper.entityToDto(comment))
                        .collect(Collectors.toList()))
                .build();

            /*
    CONTINUAR CON ESTE MAPPER
     */
    }



}
