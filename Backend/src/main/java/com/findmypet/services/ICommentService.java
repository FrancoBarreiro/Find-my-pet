package com.findmypet.services;

import com.findmypet.persistence.entities.Comment;

public interface ICommentService {

    Comment addComment(Comment comment);
}
