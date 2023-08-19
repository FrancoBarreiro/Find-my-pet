package com.findmypet.mappers;

import com.findmypet.dtos.CommentDto;
import com.findmypet.persistence.entities.Comment;
import com.findmypet.persistence.entities.Post;
import com.findmypet.persistence.entities.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentMapper {
    public static CommentDto entityToDto(Comment comment) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .date(comment.getDate().format(dateFormatter))
                .hour(comment.getDate().format(timeFormatter))
                .userId(comment.getUser().getId())
                .postId(comment.getPost().getId())
                .build();
    }

    public static Comment dtoToEntity(CommentDto comment) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime date = LocalDateTime.parse(
                comment.getDate() + " " + comment.getHour(),
                localDateTimeFormatter
        );

        return Comment.builder()
                .id(comment.getId())
                .text(comment.getText())
                .date(date)
                .user(User.builder().id(comment.getUserId()).build())
                .post(Post.builder().id(comment.getPostId()).build())
                .build();
    }
}
