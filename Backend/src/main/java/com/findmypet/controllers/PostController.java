package com.findmypet.controllers;

import com.findmypet.persistence.entities.Post;
import com.findmypet.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    @Autowired
    private IPostService postService;

    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.addPost(post));
    }
}