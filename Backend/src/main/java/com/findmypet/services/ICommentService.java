package com.findmypet.services;

import com.findmypet.dtos.CommentDto;

public interface ICommentService {

    CommentDto addComment(CommentDto comment);
}
