package com.findmypet.services.impl;

import com.findmypet.dtos.CommentDto;
import com.findmypet.exceptions.BadRequestException;
import com.findmypet.mappers.CommentMapper;
import com.findmypet.persistence.entities.Comment;
import com.findmypet.persistence.repositories.ICommentRepository;
import com.findmypet.services.ICommentService;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

    private final Validator validator;

    @Autowired
    private ICommentRepository commentRepository;

    public CommentService(Validator validator) {
        this.validator = validator;
    }

    @Override
    public CommentDto addComment(CommentDto comment) {

        if(validator.validate(comment).isEmpty()){
            Comment commentToSave = CommentMapper.dtoToEntity(comment);
            commentRepository.save(commentToSave);
            comment.setId(commentToSave.getId());
            return comment;
        } else {
            throw new BadRequestException("Check the fields");
        }
    }
}
