package com.findmypet.services.impl;

import com.findmypet.dtos.CommentDto;
import com.findmypet.exceptions.BadRequestException;
import com.findmypet.exceptions.ResourceNotFoundException;
import com.findmypet.mappers.CommentMapper;
import com.findmypet.persistence.entities.Comment;
import com.findmypet.persistence.repositories.ICommentRepository;
import com.findmypet.services.ICommentService;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        if (validator.validate(comment).isEmpty()) {
            Comment commentToSave = CommentMapper.dtoToEntity(comment);
            commentRepository.save(commentToSave);
            comment.setId(commentToSave.getId());
            return comment;
        } else {
            throw new BadRequestException("Check the fields");
        }
    }

    @Override
    public CommentDto getCommentById(Long id) {

        Optional<Comment> commentFound = commentRepository.findById(id);

        if (commentFound.isPresent()) {
            return CommentMapper.entityToDto(commentFound.get());
        } else {
            throw new ResourceNotFoundException("Comment not found");
        }
    }

    @Override
    public Page<CommentDto> getCommentsPaged(Long idPost, int size, int page) {
        Sort sort = Sort.by("date").ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Comment> commentsFound = commentRepository.findAllByPostId(idPost, pageable);

        List<CommentDto> comments = new ArrayList<>();

        for (Comment c : commentsFound) {
            CommentDto comment = CommentMapper.entityToDto(c);
            comments.add(comment);
        }
        return new PageImpl<>(comments, commentsFound.getPageable(), commentsFound.getTotalElements());
    }

    @Override
    public CommentDto updateComment(CommentDto comment) {

        Optional<Comment> commentFound = commentRepository.findById(comment.getId());

        if (commentFound.isPresent() && validator.validate(comment).isEmpty()) {
            commentRepository.save(CommentMapper.dtoToEntity(comment));
            return comment;
        } else {
            throw new BadRequestException("Check the fields");
        }
    }

    @Override
    public void deleteComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);

        if (comment.isPresent()) {
            commentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Comment not found");
        }
    }
}
