package com.findmypet.services;

import com.findmypet.dtos.CommentDto;
import org.springframework.data.domain.Page;

public interface ICommentService {

    CommentDto addComment(CommentDto comment);

    CommentDto getCommentById(Long id);

    Page<CommentDto> getCommentsPaged(Long idPost, int size, int page);

    CommentDto updateComment(CommentDto comment);

    void deleteComment(Long id);
}
