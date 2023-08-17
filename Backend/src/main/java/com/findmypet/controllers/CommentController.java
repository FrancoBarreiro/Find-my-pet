package com.findmypet.controllers;

import com.findmypet.persistence.entities.Comment;
import com.findmypet.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody Comment comment){
        return ResponseEntity.ok(commentService.addComment(comment));
    }
}
