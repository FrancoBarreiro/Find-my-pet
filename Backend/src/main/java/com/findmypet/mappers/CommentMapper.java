package com.findmypet.mappers;

import com.findmypet.dtos.CommentDto;
import com.findmypet.persistence.entities.Comment;

public class CommentMapper {
    public static CommentDto entityToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        return commentDto;

           /*     private Long id;

    private String text;

    private String date;

    private UserDto user;

    private PostDto post;
    }
}
