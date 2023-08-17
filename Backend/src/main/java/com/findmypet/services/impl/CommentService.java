package com.findmypet.services.impl;

import com.findmypet.persistence.entities.Comment;
import com.findmypet.persistence.repositories.ICommentRepository;
import com.findmypet.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
